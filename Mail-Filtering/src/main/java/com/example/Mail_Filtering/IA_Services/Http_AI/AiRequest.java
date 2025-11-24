package com.example.Mail_Filtering.IA_Services.Http_AI;

public class AiRequest {
    private String model;
    private String prompt;
    private boolean stream = false;

    public AiRequest() {
        this.model = model;
        this.prompt = prompt;
        this.stream = stream;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = "llama3";
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public boolean isStream() {
        return stream;
    }

    public void setStream(boolean stream) {
        this.stream = stream;
    }


}
