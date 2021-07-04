package com.hack.azure.mediknot.exception;

public class DoctorException extends RuntimeException{

    private int statusCode;

    public DoctorException(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
