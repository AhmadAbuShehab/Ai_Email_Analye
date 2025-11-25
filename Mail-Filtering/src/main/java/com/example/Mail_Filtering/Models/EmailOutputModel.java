package com.example.Mail_Filtering.Models;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class EmailOutputModel {

    private String absender;
    private String empfaenger;
    private  String betriff;
    //private String body;
    private ArrayList<String> body = new ArrayList<>();
    private String abholeOrt;
    private String zuStellOrt;
    private LocalDateTime abholZeit;
    private LocalDateTime zuStellZeit;


    public EmailOutputModel() {
        // Empty constructor REQUIRED by Spring
    }

    public EmailOutputModel(String ebsender, String empfaenger, String betriff, String body) {
        this.absender = ebsender;
        this.empfaenger = empfaenger;
        this.betriff = betriff;
        //this.body = body;
    }

    public EmailOutputModel(String ebsender, String empfaenger, String betriff,
                            String zuStellOrt, String abholeOrt, LocalDateTime abholZeit,
                            LocalDateTime zuStellZeit, ArrayList<String> body) {
        this.absender = ebsender;
        this.empfaenger = empfaenger;
        this.betriff = betriff;
        //this.body = body;
        this.zuStellOrt = zuStellOrt;
        this.abholeOrt = abholeOrt;
        this.abholZeit = abholZeit;
        this.zuStellZeit = zuStellZeit;
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
/*
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
*/
    public String getAbholeOrt() {
        return abholeOrt;
    }

    public void setAbholeOrt(String abholeOrt) {
        this.abholeOrt = abholeOrt;
    }

    public LocalDateTime getZuStellZeit() {
        return zuStellZeit;
    }

    public void setZuStellZeit(LocalDateTime zuStellZeit) {
        this.zuStellZeit = zuStellZeit;
    }

    public LocalDateTime getAbholZeit() {
        return abholZeit;
    }

    public void setAbholZeit(LocalDateTime abholZeit) {
        this.abholZeit = abholZeit;
    }

    public String getZuStellOrt() {
        return zuStellOrt;
    }

    public void setZuStellOrt(String zuStellOrt) {
        this.zuStellOrt = zuStellOrt;
    }

    public ArrayList<String> getBody() {
        return body;
    }

    public void setBody() {
        this.body.add(abholeOrt);
        this.body.add(zuStellOrt);
        this.body.add(abholZeit.toString());
        this.body.add(zuStellZeit.toString());
    }
}
