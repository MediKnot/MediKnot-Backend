package com.hack.azure.mediknot.exception;

public class MedicineException extends RuntimeException{
    int statusCode;

    public MedicineException(String message, int code){
        super(message);
        statusCode = code;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
