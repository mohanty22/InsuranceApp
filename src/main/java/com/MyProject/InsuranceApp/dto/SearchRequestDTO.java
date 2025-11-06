package com.MyProject.InsuranceApp.dto;



public class SearchRequestDTO {
    private String touchpoint;
    private String instype;


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
}
