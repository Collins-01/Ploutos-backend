package com.collins.ploutos.ploutos.dto.response;

public class LoginResponse {
    private String token;
    private String type;
    private UserModel user;

    public LoginResponse(String token, String type, UserModel user) {
        this.token = token;
        this.type = type;
        this.user = user;
    }
}
