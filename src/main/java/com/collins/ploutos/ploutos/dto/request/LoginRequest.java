package com.collins.ploutos.ploutos.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

public class LoginRequest {
    @Email(message = "a valid email is required")
    private String email;

    @Min(value = 6, message = "password must be at least 6 characters long")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
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
}