package com.MyProject.InsuranceApp.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public class AppRequestDTO {
    @NotBlank(message = "Application number is required")
    private String appnum;

    @Valid
    @NotNull(message = "Lead data is required")
    private LeadDataDTO leaddata;

    @Valid
    @NotNull(message = "Quote details are required")
    private QuoteDetailsDTO quotedetails;
    private String sumassured;
    private String annpremium;
    private String prodname;
    private String instype;
    private String touchpoint;
    private String channel;
    private String planoption;
    private String freq;
    @Valid
    private List<LifeAssuredDTO> lifeassured;
    @Override
    public String toString() {
        return "Application{" +

                " appnum='" + appnum + '\'' +
                ", leaddata=" + leaddata +
                ", quotedetails=" + quotedetails +
                ", sumassured='" + sumassured + '\'' +
                ", annpremium='" + annpremium + '\'' +
                ", prodname='" + prodname + '\'' +
                ", instype='" + instype + '\'' +
                ", touchpoint='" + touchpoint + '\'' +
                ", channel='" + channel + '\'' +
                ", planoption='" + planoption + '\'' +
                ", freq='" + freq + '\'' +
                ", lifeassured=" + lifeassured +
                '}';
    }



    public List<LifeAssuredDTO> getLifeassured() {
        return lifeassured;
    }

    public void setLifeassured(List<LifeAssuredDTO> lifeassured) {
        this.lifeassured = lifeassured;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public String getPlanoption() {
        return planoption;
    }

    public void setPlanoption(String planoption) {
        this.planoption = planoption;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTouchpoint() {
        return touchpoint;
    }

    public void setTouchpoint(String touchpoint) {
        this.touchpoint = touchpoint;
    }

    public String getInstype() {
        return instype;
    }

    public void setInstype(String instype) {
        this.instype = instype;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getAnnpremium() {
        return annpremium;
    }

    public void setAnnpremium(String annpremium) {
        this.annpremium = annpremium;
    }

    public String getSumassured() {
        return sumassured;
    }

    public void setSumassured(String sumassured) {
        this.sumassured = sumassured;
    }

    public QuoteDetailsDTO getQuotedetails() {
        return quotedetails;
    }

    public void setQuotedetails(QuoteDetailsDTO quotedetails) {
        this.quotedetails = quotedetails;
    }

    public LeadDataDTO getLeaddata() {
        return leaddata;
    }

    public void setLeaddata(LeadDataDTO leaddata) {
        this.leaddata = leaddata;
    }

    public String getAppnum() {
        return appnum;
    }

    public void setAppnum(String appnum) {
        this.appnum = appnum;
    }

    public AppRequestDTO( String appnum, LeadDataDTO leaddata, QuoteDetailsDTO quotedetails, String sumassured, String annpremium, String prodname, String instype, String touchpoint, String channel, String planoption, String freq, List<LifeAssuredDTO> lifeassured) {

        this.appnum = appnum;
        this.leaddata = leaddata;
        this.quotedetails = quotedetails;
        this.sumassured = sumassured;
        this.annpremium = annpremium;
        this.prodname = prodname;
        this.instype = instype;
        this.touchpoint = touchpoint;
        this.channel = channel;
        this.planoption = planoption;
        this.freq = freq;
        this.lifeassured = lifeassured;
    }

    public AppRequestDTO() {
    }


}

