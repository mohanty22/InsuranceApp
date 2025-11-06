
package com.MyProject.InsuranceApp.dto;

public class SearchResponseDTO {
    private String applicationNum;
    private String name;
    private int age;
    private String gender;
    private String prodname;
    private String annpremium;

    public String getApplicationNum() {
        return applicationNum;
    }

    public void setApplicationNum(String applicationNum) {
        this.applicationNum = applicationNum;
    }

    public SearchResponseDTO(String applicationNum, String name, int age, String gender, String prodname, String annpremium) {
        this.applicationNum = applicationNum;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.prodname = prodname;
        this.annpremium = annpremium;
    }

    // Getters only
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getProdname() { return prodname; }
    public String getAnnpremium() { return annpremium; }
}

