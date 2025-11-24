package com.example.Mail_Filtering.IA_Services.Http_AI;

public class AiResponse {
    private String model;
    private String response;
    private boolean done = true;

    public AiResponse(String model, String prompt, boolean done) {
        this.model = model;
        this.response = prompt;
        this.done = done;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
