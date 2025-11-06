package com.MyProject.InsuranceApp.service.impl;




import com.MyProject.InsuranceApp.dto.*;
import com.MyProject.InsuranceApp.entity.*;
import com.MyProject.InsuranceApp.exception.AppAlreadyExistsException;
import com.MyProject.InsuranceApp.repo.ApplicationRepo;
import com.MyProject.InsuranceApp.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppServiceImpl implements ApplicationService {
    private final ApplicationRepo appRepo;

    @Autowired
    public AppServiceImpl(ApplicationRepo appRepo) {
        this.appRepo = appRepo;
    }

    @Override
    public Optional<Application> getApplicationByAppnum(String appnum) {
        return appRepo.findByAppnum(appnum);
    }

    @Override
    public Application createApplication(AppRequestDTO dto) {
        Application app = new Application();
        Optional<Application> existing = appRepo.findByAppnum(dto.getAppnum());
        if (existing.isPresent()) {
            throw new AppAlreadyExistsException("Application number already exists: " + dto.getAppnum());
        }

        app.setAppnum(dto.getAppnum());
        app.setSumassured(dto.getSumassured());
        app.setAnnpremium(dto.getAnnpremium());
        app.setProdname(dto.getProdname());
        app.setInstype(dto.getInstype());
        app.setTouchpoint(dto.getTouchpoint());
        app.setChannel(dto.getChannel());
        app.setPlanoption(dto.getPlanoption());
        app.setFreq(dto.getFreq());

        // Map LeadData
        LeadDataDTO leadDto = dto.getLeaddata();
        if (leadDto != null) {
            LeadData lead = new LeadData();
            lead.setFirstnm(leadDto.getFirstnm());
            lead.setLastnm(leadDto.getLastnm());
            lead.setMobno(leadDto.getMobno());
            lead.setEmail(leadDto.getEmail());
            lead.setDob(leadDto.getDob());
            lead.setSex(leadDto.getSex());
            lead.setLeadsrc(leadDto.getLeadsrc());
            app.setLeaddata(lead);
        }

        // Map QuoteDetails
        QuoteDetailsDTO quoteDto = dto.getQuotedetails();
        if (quoteDto != null) {
            QuoteDetails quote = new QuoteDetails();
            quote.setPacAprflg(quoteDto.getPacAprflg());
            quote.setPan_no(quoteDto.getPan_no());

            // Map PlanDetails inside QuoteDetails
            PlanDetailsDTO planDto = quoteDto.getPlanDetails();
            if (planDto != null) {
                PlanDetails plan = new PlanDetails();
                plan.setSumAssured(planDto.getSumAssured());
                plan.setSumPremium(planDto.getSumPremium());
                plan.setAnnulImcome(planDto.getAnnulImcome());
                plan.setSubProduct(planDto.getSubProduct());
                plan.setPpt(planDto.getPpt());

                quote.setPlanDetails(plan);
            }

            app.setQuotedetails(quote);
        }

        // Map LifeAssured
        if (dto.getLifeassured() != null && !dto.getLifeassured().isEmpty()) {
            List<LifeAssured> assuredList = dto.getLifeassured().stream().map(laDto -> {
                LifeAssured la = new LifeAssured();
                la.setName(laDto.getName());
                la.setGender(laDto.getGender());
                return la;
            }).collect(Collectors.toList());

            app.setLifeassured(assuredList);
        }

        return appRepo.save(app);
    }
    @Override
    public List<SearchResponseDTO> searchByTouchpointAndInstype(String touchpoint, String instype) {
        return appRepo.findByTouchpointAndInstype(touchpoint, instype).stream()
                .map(app -> {
                    String appnum = app.getAppnum();
                    String name = app.getLeaddata().getFirstnm() + " " + app.getLeaddata().getLastnm();
                    String gender = app.getLeaddata().getSex();
                    int age = calculateAge(app.getLeaddata().getDob());
                    return new SearchResponseDTO(appnum,name, age, gender, app.getProdname(), app.getAnnpremium());
                })
                .toList();
    }

    private int calculateAge(String dobString) {
        LocalDate dob = LocalDate.parse(dobString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return Period.between(dob, LocalDate.now()).getYears();
    }

}
