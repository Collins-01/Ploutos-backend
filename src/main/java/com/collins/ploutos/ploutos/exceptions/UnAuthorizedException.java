package com.collins.ploutos.ploutos.exceptions;

import org.springframework.http.HttpStatus;
import com.collins.ploutos.ploutos.exceptions.BaseException;

public class UnAuthorizedException extends BaseException {
    public UnAuthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
