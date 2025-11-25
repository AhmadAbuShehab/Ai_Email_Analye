package com.example.Mail_Filtering.Models;
import com.example.Mail_Filtering.Models.EmailOutputModel;


import java.util.List;



public class EmailContainerModel {

    EmailOutputModel emailOutputModel;

    private List<EmailOutputModel> orders;

    public List<EmailOutputModel> getOrders() {
        return orders;
    }

    public void setOrders(List<EmailOutputModel> orders) {
        this.orders = orders;
    }

}
