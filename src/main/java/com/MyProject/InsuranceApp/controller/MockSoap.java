package com.MyProject.InsuranceApp.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class MockSoap {
    @GetMapping(value = "/mock-pan-kyc", produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> getMockPanKyc() throws IOException {
        // Load the XML file from resources folder
        String xml = StreamUtils.copyToString(
                new ClassPathResource("mockPanKycResponse.xml").getInputStream(),
                StandardCharsets.UTF_8
        );
        return ResponseEntity.ok(xml);
    }
}
