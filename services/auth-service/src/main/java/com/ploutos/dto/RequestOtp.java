
package com.ploutos.dto;

public class RequestOtp {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RequestOtp(String email) {
        this.email = email;
    }
}
