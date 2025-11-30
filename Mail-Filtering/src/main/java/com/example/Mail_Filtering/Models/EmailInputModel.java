package com.example.Mail_Filtering.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;


//@Entity //erst mal als Component definieren aber sobals DB verbindung benötigt wird, entweder
// als Entity definieren oder eine Entity  klass erstellen
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class EmailInputModel {

    //@Id // Primärschlüssel
    //private Long id;
    private String absender;
    private String empfaenger;
    private  String betriff;
    private String body;
    private String status;


/*  id als PK für die DB Tabelle
    public EmailInputModel(Long id) {
        this.id = id;
    }*/

    public EmailInputModel(String absender, String empfaenger, String betriff, String body) {
        this.absender = absender;
        this.empfaenger = empfaenger;
        this.betriff = betriff;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
