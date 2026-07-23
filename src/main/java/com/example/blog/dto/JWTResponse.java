package com.example.blog.dto;


//jwt token part 1
public class JWTResponse {
    private String token;

    public JWTResponse(String token){
        this.token=token;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
