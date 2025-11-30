package com.example.Mail_Filtering.Models;


public class PdfTextResponseModel {

    private String fileName;
    private int textLength;
    private String content;
    private String status;

    // Konstruktor, Getter und Setter hier
    // (oder verwenden Sie ein Java 16+ record für Einfachheit)
    public PdfTextResponseModel(String fileName, int textLength, String content, String status) {
        this.fileName = fileName;
        this.textLength = textLength;
        this.content = content;
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getTextLength() {
        return textLength;
    }

    public void setTextLength(int textLength) {
        this.textLength = textLength;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
