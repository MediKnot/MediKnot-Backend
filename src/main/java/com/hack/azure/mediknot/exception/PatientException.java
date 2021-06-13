package com.hack.azure.mediknot.exception;

public class PatientException extends RuntimeException{
    private int statusCode;
    public PatientException(String message, int code){
        super(message);
        statusCode = code;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
