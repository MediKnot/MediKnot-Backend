package com.hack.azure.mediknot.exception;

public class UserException extends RuntimeException{

    private int statusCode;

    public UserException(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
