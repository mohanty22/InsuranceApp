package com.MyProject.InsuranceApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LeadDataDTO {
    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[A-Za-z ]{1,30}$", message = "First name must only contain letters and be max 30 characters")
    private String firstnm;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[A-Za-z ]{1,30}$", message = "Last name must only contain letters and be max 30 characters")
    private String lastnm;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Mobile number must be a valid 10-digit Indian number")
    private String mobno;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;



    @NotBlank(message = "Date of birth is required")
    private String dob;
    @Pattern(regexp = "^(M|F|O)$", message = "SEX must be M, F, or O")
    private String sex;


    private String leadsrc;
    public LeadDataDTO() {}

    public LeadDataDTO(String firstnm, String lastnm, String mobno, String email, String dob,String sex, String leadsrc) {
        this.firstnm = firstnm;
        this.lastnm = lastnm;
        this.mobno = mobno;
        this.email = email;
        this.dob = dob;
        this.sex = sex;
        this.leadsrc = leadsrc;
    }

    @Override
    public String toString() {
        return "LeadData{" +

                " firstnm='" + firstnm + '\'' +
                ", lastnm='" + lastnm + '\'' +
                ", mobno='" + mobno + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", sex='" + sex + '\'' +
                ", leadsrc='" + leadsrc + '\'' +
                '}';
    }



    public String getLeadsrc() {
        return leadsrc;
    }

    public void setLeadsrc(String leadsrc) {
        this.leadsrc = leadsrc;
    }

    public String getDob() {
        return dob;
    }

    public  String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastnm() {
        return lastnm;
    }

    public void setLastnm(String lastnm) {
        this.lastnm = lastnm;
    }

    public String getFirstnm() {
        return firstnm;
    }

    public void setFirstnm(String firstnm) {
        this.firstnm = firstnm;
    }
}
