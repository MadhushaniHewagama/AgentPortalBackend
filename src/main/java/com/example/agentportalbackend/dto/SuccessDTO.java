package com.example.agentportalbackend.dto;

public class SuccessDTO {
    String message;

    public SuccessDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
