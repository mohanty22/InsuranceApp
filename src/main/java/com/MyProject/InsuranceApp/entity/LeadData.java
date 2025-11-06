package com.MyProject.InsuranceApp.entity;

import jakarta.persistence.*;

@Entity
public class LeadData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstnm;
    private String lastnm;
    private String mobno;
    private String email;
    private String dob;
    private String leadsrc;
    private String sex;

    public LeadData() {}

    public LeadData(String firstnm, String lastnm, String mobno,String email, String dob, String sex,String leadsrc) {
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
                "id=" + id +
                ", firstnm='" + firstnm + '\'' +
                ", lastnm='" + lastnm + '\'' +
                ", mobno='" + mobno + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", sex='" + sex + '\'' +
                ", leadsrc='" + leadsrc + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
