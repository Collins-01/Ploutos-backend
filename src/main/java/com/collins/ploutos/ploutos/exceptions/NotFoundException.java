package com.collins.ploutos.ploutos.exceptions;
import org.springframework.http.HttpStatus;
import com.collins.ploutos.ploutos.exceptions.BaseException;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
