package com.example.agentportalbackend.dto;

public class Token {
    String token;
    Long id;
    String userType;

    public Token(String token, Long id, String userType) {
        this.token = token;
        this.id = id;
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
