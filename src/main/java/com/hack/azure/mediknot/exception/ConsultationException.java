package com.hack.azure.mediknot.exception;

public class ConsultationException extends RuntimeException{
    private int statusCode;

    public ConsultationException(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
