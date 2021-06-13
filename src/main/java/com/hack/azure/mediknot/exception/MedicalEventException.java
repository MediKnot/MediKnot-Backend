package com.hack.azure.mediknot.exception;

public class MedicalEventException extends RuntimeException{
    private int statusCode;

    public MedicalEventException(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
