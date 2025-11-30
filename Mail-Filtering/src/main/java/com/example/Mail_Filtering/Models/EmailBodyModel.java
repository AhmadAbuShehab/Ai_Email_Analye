package com.example.Mail_Filtering.Models;
import java.time.LocalDateTime;



public class EmailBodyModel {

    private String order;
    private String ware;
    private String abholort;
    private String abholzeit;
    private String zustellort;
    private String zustellzeit;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getWare() {
        return ware;
    }

    public void setWare(String ware) {
        this.ware = ware;
    }

    public String getAbholort() {
        return abholort;
    }

    public void setAbholort(String abholort) {
        this.abholort = abholort;
    }

    public String getAbholzeit() {
        return abholzeit;
    }

    public void setAbholzeit(String abholzeit) {
        this.abholzeit = abholzeit;
    }

    public String getZustellzeit() {
        return zustellzeit;
    }

    public void setZustellzeit(String zustellzeit) {
        this.zustellzeit = zustellzeit;
    }

    public String getZustellort() {
        return zustellort;
    }

    public void setZustellort(String zustellort) {
        this.zustellort = zustellort;
    }
}
