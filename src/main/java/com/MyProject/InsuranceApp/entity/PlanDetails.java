package com.MyProject.InsuranceApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PlanDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  Long sumAssured;
    private  Long sumPremium;
    private  Long AnnulImcome;
    private String subProduct;
    private int ppt;

    public PlanDetails() {
    }

    public PlanDetails(  Long sumPremium, Long sumAssured,int ppt, String subProduct, Long annulImcome) {


        this.sumPremium = sumPremium;
        this.sumAssured = sumAssured;
        this.ppt = ppt;
        this.subProduct = subProduct;
        AnnulImcome = annulImcome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPpt() {
        return ppt;
    }

    public void setPpt(int ppt) {
        this.ppt = ppt;
    }

    public String getSubProduct() {
        return subProduct;
    }

    public void setSubProduct(String subProduct) {
        this.subProduct = subProduct;
    }

    public Long getAnnulImcome() {
        return AnnulImcome;
    }

    public void setAnnulImcome(Long annulImcome) {
        AnnulImcome = annulImcome;
    }

    public Long getSumPremium() {
        return sumPremium;
    }

    public void setSumPremium(Long sumPremium) {
        this.sumPremium = sumPremium;
    }

    public Long getSumAssured() {
        return sumAssured;
    }

    public void setSumAssured(Long sumAssured) {
        this.sumAssured = sumAssured;
    }

    @Override
    public String toString() {
        return "PlanDetails{" +
                "id=" + id +
                ", sumAssured=" + sumAssured +
                ", sumPremium=" + sumPremium +
                ", AnnulImcome=" + AnnulImcome +
                ", subProduct='" + subProduct + '\'' +
                ", ppt=" + ppt +
                '}';
    }
}
