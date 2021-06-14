package com.hack.azure.mediknot.exception;

public class PrescriptionException extends RuntimeException{
    private int statusCode;
    public PrescriptionException(String message, int code){
        super(message);
        statusCode = code;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
