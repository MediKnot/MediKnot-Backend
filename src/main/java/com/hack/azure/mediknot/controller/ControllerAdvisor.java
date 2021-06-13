package com.hack.azure.mediknot.controller;

import com.hack.azure.mediknot.exception.DoctorException;
import com.hack.azure.mediknot.exception.PatientException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DoctorException.class)
    public ResponseEntity<String> handleException(DoctorException ex){
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(PatientException.class)
    public ResponseEntity<String> handleException(PatientException ex){
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

}
