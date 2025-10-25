package com.collins.ploutos.ploutos.exceptions;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> errors;

    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
        this(timestamp, status, error, message, path, null);
    }

    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path,
            Map<String, String> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.errors = errors != null ? errors : new HashMap<>();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ErrorResponse [timestamp=" + timestamp + ", status=" + status + ", error=" + error + ", message="
                + message + ", path=" + path + ", errors=" + errors + "]";
    }

}
