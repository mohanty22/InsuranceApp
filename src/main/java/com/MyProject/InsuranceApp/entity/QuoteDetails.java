package com.MyProject.InsuranceApp.entity;

import jakarta.persistence.*;
@Entity
public class QuoteDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pacAprflg;
    private String pan_no;

    @OneToOne(cascade = CascadeType.ALL)
    private PlanDetails planDetails;

    public QuoteDetails() {}

    public QuoteDetails(String pacAprflg, String pan_no, PlanDetails planDetails) {
        this.pacAprflg = pacAprflg;
        this.pan_no = pan_no;
        this.planDetails = planDetails;
    }


    @Override
    public String toString() {
        return "QuoteDetails{" +
                "id=" + id +
                ", pacAprflg='" + pacAprflg + '\'' +
                ", pan_no='" + pan_no + '\'' +
                ", planDetails=" + planDetails +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getPan_no() {
        return pan_no;
    }

    public void setPan_no(String pan_no) {
        this.pan_no = pan_no;
    }

    public String getPacAprflg() {
        return pacAprflg;
    }

    public PlanDetails getPlanDetails() {
        return planDetails;
    }

    public void setPlanDetails(PlanDetails planDetails) {
        this.planDetails = planDetails;
    }

    public void setPacAprflg(String pacAprflg) {
        this.pacAprflg = pacAprflg;
    }
}

