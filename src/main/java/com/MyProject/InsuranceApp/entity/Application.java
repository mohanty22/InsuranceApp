package com.MyProject.InsuranceApp.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity

public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appnum;

    @OneToOne(cascade = CascadeType.ALL)
    private LeadData leaddata;

    @OneToOne(cascade = CascadeType.ALL)
    private QuoteDetails quotedetails;

    private String sumassured;
    private String annpremium;
    private String prodname;
    private String instype;
    private String touchpoint;
    private String channel;
    private String planoption;
    private String freq;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LifeAssured> lifeassured;


    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", appnum='" + appnum + '\'' +
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LifeAssured> getLifeassured() {
        return lifeassured;
    }

    public void setLifeassured(List<LifeAssured> lifeassured) {
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

    public QuoteDetails getQuotedetails() {
        return quotedetails;
    }

    public void setQuotedetails(QuoteDetails quotedetails) {
        this.quotedetails = quotedetails;
    }

    public LeadData getLeaddata() {
        return leaddata;
    }

    public void setLeaddata(LeadData leaddata) {
        this.leaddata = leaddata;
    }

    public String getAppnum() {
        return appnum;
    }

    public void setAppnum(String appnum) {
        this.appnum = appnum;
    }

    public Application(Long id, String appnum, LeadData leaddata, QuoteDetails quotedetails, String sumassured, String annpremium, String prodname, String instype, String touchpoint, String channel, String planoption, String freq, List<LifeAssured> lifeassured) {
        this.id = id;
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

    public Application() {
    }


}
