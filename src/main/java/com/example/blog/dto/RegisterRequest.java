package com.example.blog.dto;


import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {
    @NotBlank(message = "Email cannot be empty") // Validation
    private String email;

    @NotBlank(message = "password cannot be empty") // Validation
    private String password;

    @NotBlank(message = "username can not be empty")
    private String username;

    private String name;

    public RegisterRequest(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
