package com.MyProject.InsuranceApp.dto;

public class PlanDetailsDTO {
    private  Long sumAssured;
    private  Long sumPremium;
    private  Long AnnulImcome;
    private String subProduct;
    private int ppt;

    public PlanDetailsDTO() {
    }

    public PlanDetailsDTO(  Long sumPremium, Long sumAssured,int ppt, String subProduct, Long annulImcome) {


        this.sumPremium = sumPremium;
        this.sumAssured = sumAssured;
        this.ppt = ppt;
        this.subProduct = subProduct;
        AnnulImcome = annulImcome;
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

                " sumAssured=" + sumAssured +
                ", sumPremium=" + sumPremium +
                ", AnnulImcome=" + AnnulImcome +
                ", subProduct='" + subProduct + '\'' +
                ", ppt=" + ppt +
                '}';
    }
}
