package com.collins.ploutos.ploutos.dto.response;

import com.collins.ploutos.ploutos.model.UserModel;

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
