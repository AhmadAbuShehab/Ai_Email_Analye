package com.example.Mail_Filtering.Models;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmailModel {

    private String absender;
    private String empfaenger;
    private  String betriff;
    private String body;
    private String abholeOrt;
    private String zuStellOrt;
    private LocalDateTime abholZeit;
    private LocalDateTime zuStellZeit;


    public EmailModel() {
        // Empty constructor REQUIRED by Spring
    }

    public EmailModel(String ebsender, String empfaenger, String betriff, String body) {
        this.absender = ebsender;
        this.empfaenger = empfaenger;
        this.betriff = betriff;
        this.body = body;
    }

    public EmailModel(String ebsender, String empfaenger, String betriff, String body,
                      String zuStellOrt, String abholeOrt, LocalDateTime abholZeit, LocalDateTime zuStellZeit) {
        this.absender = ebsender;
        this.empfaenger = empfaenger;
        this.betriff = betriff;
        this.body = body;
        this.zuStellOrt = zuStellOrt;
        this.abholeOrt = abholeOrt;
        this.abholZeit = abholZeit;
        this.zuStellZeit = zuStellZeit;
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
