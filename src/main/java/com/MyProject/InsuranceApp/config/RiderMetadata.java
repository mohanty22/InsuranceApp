package com.MyProject.InsuranceApp.config;

public class RiderMetadata {
    private String code;
    private String name;
    private long baseCost;

    public RiderMetadata(String code, String name, long baseCost) {
        this.code = code;
        this.name = name;
        this.baseCost = baseCost;
    }

    // Getters and Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public long getBaseCost() { return baseCost; }
    public void setBaseCost(long baseCost) { this.baseCost = baseCost; }
}
