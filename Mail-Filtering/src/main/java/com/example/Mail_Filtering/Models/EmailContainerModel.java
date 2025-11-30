package com.example.Mail_Filtering.Models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EmailContainerModel {

    //private String status;

    private List<EmailOutputModel> orders;

    public List<EmailOutputModel> getOrders() {
        return orders;
    }


    public void setOrders(List<EmailOutputModel> orders) {
        this.orders = orders;
    }

   /* public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }*/
}
