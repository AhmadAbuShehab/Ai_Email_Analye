package com.example.Mail_Filtering.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailContainerModel {

    @Autowired
    EmailModel emailModel;
    public List<EmailModel> emailModelList;

    public List<EmailModel> getEmailModelList() {
        return emailModelList;
    }

    public void setEmailModelList(List<EmailModel> emailModelList) {
        this.emailModelList = emailModelList;
    }
}
