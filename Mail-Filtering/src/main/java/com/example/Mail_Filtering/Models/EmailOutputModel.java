package com.example.Mail_Filtering.Models;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class EmailOutputModel {

    //private String order;
    //private String ware;
    private String absender;
    private String empfaenger;
    private String betriff;
    @JsonProperty("body")
    private EmailBodyModel emailBodyModel;



    public EmailOutputModel() {
        // Empty constructor REQUIRED by Spring
    }

    public EmailOutputModel(String ebsender, String empfaenger, String betriff,
                            EmailBodyModel emailBodyModel) {
        this.absender = ebsender;
        this.empfaenger = empfaenger;
        this.betriff = betriff;
        this.emailBodyModel = emailBodyModel;
    }



    public String getAbsender() {
        return absender;
    }

    public void setAbsender(String absender) {
        this.absender = absender;
    }

    public String getEmpfaenger() {
        return empfaenger;
    }

    public void setEmpfaenger(String empfaenger) {
        this.empfaenger = empfaenger;
    }

    public String getBetriff() {
        return betriff;
    }

    public void setBetriff(String betriff) {
        this.betriff = betriff;
    }

    public EmailBodyModel getEmailBodyModel() {
        return emailBodyModel;
    }

    public void setEmailBodyModel(EmailBodyModel emailBodyModel) {
        this.emailBodyModel = emailBodyModel;
    }
}
