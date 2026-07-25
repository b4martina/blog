package com.example.blog.dto;
import jakarta.validation.constraints.NotBlank;

public class LogInRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public LogInRequest(){}


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
