package com.example.Mail_Filtering.Models;

import org.springframework.stereotype.Component;

@Component
public class EmailInputModel {
    private String absender;
    private String empfaenger;
    private  String betriff;
    private String body;

    public EmailInputModel() {
        this.absender = absender;
    }

    public EmailInputModel(String absender, String empfaenger, String betriff, String body) {
        this.absender = absender;
        this.empfaenger = empfaenger;
        this.betriff = betriff;
        this.body = body;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
