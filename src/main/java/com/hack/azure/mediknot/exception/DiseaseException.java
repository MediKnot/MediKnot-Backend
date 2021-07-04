package com.hack.azure.mediknot.exception;

public class DiseaseException extends RuntimeException{

    int statusCode;

    public DiseaseException(String message, int code){
        super(message);
        statusCode = code;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
