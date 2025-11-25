package com.example.Mail_Filtering.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailContainerModel {

    @Autowired
    EmailOutputModel emailModel;
    public List<EmailOutputModel> emailModelList;

    public List<EmailOutputModel> getEmailModelList() {
        return emailModelList;
    }

    public void setEmailModelList(List<EmailOutputModel> emailModelList) {
        this.emailModelList = emailModelList;
    }
}
