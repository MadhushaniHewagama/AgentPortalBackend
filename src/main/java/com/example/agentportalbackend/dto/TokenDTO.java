package com.example.agentportalbackend.dto;

import com.example.agentportalbackend.enums.Role;

public class TokenDTO {
    String token;
    Long id;
    Role role;

    public TokenDTO(String token, Long id, Role role) {
        this.token = token;
        this.id = id;
        this.role = role;
    }
    public TokenDTO(Long id, Role role) {
        this.id = id;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
