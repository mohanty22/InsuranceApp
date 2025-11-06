package com.MyProject.InsuranceApp.service.impl;

import com.MyProject.InsuranceApp.dto.CheckCkycRequestDTO;
import com.MyProject.InsuranceApp.dto.CheckCkycResponseDTO;
import com.MyProject.InsuranceApp.dto.CkycDetailsResponseDTO;
import com.MyProject.InsuranceApp.dto.FetchCkycDetailsRequestDTO;
import com.MyProject.InsuranceApp.entity.CkycTransactionLog;
import com.MyProject.InsuranceApp.repo.CkycTransactionLogRepo;
import com.MyProject.InsuranceApp.service.CkycService;
import com.MyProject.InsuranceApp.utils.DateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CkycServiceImpl implements CkycService {

    private final RestClient restClient;
    private final ConcurrentHashMap<String, String[]> sessionStore;
    private final CkycTransactionLogRepo ckycTransactionLogRepo;
    private final ObjectMapper objectMapper;

    public CkycServiceImpl(CkycTransactionLogRepo ckycTransactionLogRepo) {
        this.restClient = RestClient.create("http://localhost:8080");
        this.sessionStore = new ConcurrentHashMap<>();
        this.ckycTransactionLogRepo = ckycTransactionLogRepo;
        this.objectMapper = new ObjectMapper();
    }

    // Helper to serialize DTO to JSON
    private String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "JSON serialization error: " + e.getMessage();
        }
    }

    @Override
    public CheckCkycResponseDTO checkCkyc(CheckCkycRequestDTO dto) {
        String serviceName = "CHECK_CKYC";
        CkycTransactionLog log = new CkycTransactionLog();
        CheckCkycResponseDTO response = null;

        try {
            log.setServiceName(serviceName);
            log.setAppnum(dto.getApplicationNumber());
            log.setRequestPayload(toJson(dto));

            String soapResponse = restClient.get()
                    .uri("/mock-pan-kyc")
                    .retrieve()
                    .body(String.class);

            log.setResponsePayload(soapResponse);

            String sessionId = UUID.randomUUID().toString();
            log.setSessionId(sessionId);
            String status = "NO_MATCH";

            Document doc = parseXmlString(soapResponse);

            NodeList peopleNodes = doc.getElementsByTagName("pank:people");
            for (int i = 0; i < peopleNodes.getLength(); i++) {
                Node personNode = peopleNodes.item(i);
                String panNumber = getTagValueFromNode("pank:panNumber", personNode);
                String mobileNumber = getTagValueFromNode("pank:mobileNumber", personNode);

                if (dto.getPanNumber().equals(panNumber) && dto.getMobileNumber().equals(mobileNumber)) {
                    String otp = generateOtp();
                    sessionStore.put(sessionId, new String[]{dto.getPanNumber(), otp});
                    response = new CheckCkycResponseDTO("Success! OTP sent to front end.", otp, sessionId);
                    status = "SUCCESS";
                    break;
                }
            }

            if (response == null) {
                response = new CheckCkycResponseDTO("No matching details found.", null, null);
            }
            log.setStatus(status);

        } catch (Exception e) {
            log.setStatus("FAILURE");
            log.setResponsePayload(log.getResponsePayload() != null ? log.getResponsePayload() + "\nERROR: " + e.getMessage() : "API call failed. ERROR: " + e.getMessage());
            response = new CheckCkycResponseDTO("Error processing KYC details.", null, null);
            e.printStackTrace();
        } finally {
            ckycTransactionLogRepo.save(log);
        }

        return response;
    }

    @Override
    public CkycDetailsResponseDTO fetchCkycDetails(FetchCkycDetailsRequestDTO dto) {
        String serviceName = "FETCH_CKYC_DETAILS";
        CkycTransactionLog log = new CkycTransactionLog();
        CkycDetailsResponseDTO ckycDetails = null;

        try {
            log.setServiceName(serviceName);
            log.setSessionId(dto.getSessionId());
            log.setRequestPayload(toJson(dto));
            // In a real application, you would retrieve the appnum from the session store
            // For now, we link the log with the PAN number from the request as a placeholder for correlation
            log.setAppnum(dto.getPanNumber());

            String[] sessionData = sessionStore.get(dto.getSessionId());

            if (sessionData == null || !dto.getPanNumber().equals(sessionData[0]) || !dto.getOtp().equals(sessionData[1])) {
                log.setStatus("UNAUTHORIZED");
                log.setResponsePayload("Validation failed: Session or OTP mismatch.");
                return null;
            }

            String soapResponse = restClient.get()
                    .uri("/mock-pan-kyc")
                    .retrieve()
                    .body(String.class);

            log.setResponsePayload(soapResponse);

            Document doc = parseXmlString(soapResponse);
            NodeList peopleNodes = doc.getElementsByTagName("pank:people");
            String status = "NO_MATCH_FOUND";

            for (int i = 0; i < peopleNodes.getLength(); i++) {
                Node personNode = peopleNodes.item(i);
                String panNumber = getTagValueFromNode("pank:panNumber", personNode);

                if (dto.getPanNumber().equals(panNumber)) {
                    ckycDetails = new CkycDetailsResponseDTO();
                    Node ckycNode = getNodeByTagName("pank:ckycDetails", personNode);
                    if (ckycNode != null) {
                        ckycDetails.setFullName(getTagValueFromNode("pank:fullName", ckycNode));
                        ckycDetails.setAddress(getTagValueFromNode("pank:address", ckycNode));
                        ckycDetails.setOccupation(getTagValueFromNode("pank:occupation", ckycNode));
                        ckycDetails.setMotherName(getTagValueFromNode("pank:motherName", ckycNode));
                        ckycDetails.setFatherName(getTagValueFromNode("pank:fatherName", ckycNode));
                        ckycDetails.setOrganization(getTagValueFromNode("pank:organization", ckycNode));
                        ckycDetails.setEmail(getTagValueFromNode("pank:email", ckycNode));
                        ckycDetails.setNationality(getTagValueFromNode("pank:nationality", ckycNode));
                        ckycDetails.setMaritalStatus(getTagValueFromNode("pank:maritalStatus", ckycNode));
                        ckycDetails.setAlternateContact(getTagValueFromNode("pank:alternateContact", ckycNode));
                        ckycDetails.setGender(getTagValueFromNode("pank:gender", personNode));

                        String dobString = getTagValueFromNode("pank:dob", personNode);
                        if (dobString != null) {
                            try {
                                // Note: DateUtils.parseDate assumes "dd-MMM-yyyy" format from your utility file
                                LocalDate dob = DateUtils.parseDate(dobString);
                                ckycDetails.setDob(dob);
                            } catch (Exception dateEx) {
                                // Handle case where DOB is in a different format or missing
                                System.err.println("Could not parse DOB from XML: " + dobString + ". Error: " + dateEx.getMessage());
                            }
                        }
                    }
                    sessionStore.remove(dto.getSessionId());
                    status = "SUCCESS";
                    break;
                }
            }
            log.setStatus(status);

        } catch (Exception e) {
            log.setStatus("FAILURE");
            log.setResponsePayload(log.getResponsePayload() != null ? log.getResponsePayload() + "\nERROR: " + e.getMessage() : "API call failed. ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ckycTransactionLogRepo.save(log);
        }

        return ckycDetails;
    }

    private Document parseXmlString(String xmlString) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        return db.parse(new InputSource(new StringReader(xmlString)));
    }

    private String getTagValueFromNode(String tag, Node parentNode) {
        if (parentNode.getNodeType() == Node.ELEMENT_NODE) {
            NodeList nodeList = parentNode.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getLocalName().equals(tag.split(":")[1])) {
                    return node.getTextContent();
                }
            }
        }
        return null;
    }

    private Node getNodeByTagName(String tag, Node parentNode) {
        if (parentNode.getNodeType() == Node.ELEMENT_NODE) {
            NodeList nodeList = parentNode.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getLocalName().equals(tag.split(":")[1])) {
                    return node;
                }
            }
        }
        return null;
    }

    private String generateOtp() {
        return String.format("%06d", ThreadLocalRandom.current().nextInt(1000000));
    }
}