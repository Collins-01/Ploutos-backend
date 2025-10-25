package com.collins.ploutos.ploutos.exceptions;
import org.springframework.http.HttpStatus;
public class ErrorResponse {
    private int statusCode;
    private String status;
    private String message;
    private long timestamp;
    private String path;

    public ErrorResponse(int statusCode, String message, long timestamp, String path) {
        this.statusCode = statusCode;
        this.status = HttpStatus.valueOf(statusCode).name();
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
