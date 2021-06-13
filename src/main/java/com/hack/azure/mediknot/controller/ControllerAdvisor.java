package com.hack.azure.mediknot.controller;

import com.hack.azure.mediknot.exception.*;
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

    @ExceptionHandler(DiseaseException.class)
    public ResponseEntity<String> handleException(DiseaseException ex){
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(MedicineException.class)
    public ResponseEntity<String> handleException(MedicineException ex){
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(MedicalEventException.class)
    public ResponseEntity<String> handleException(MedicalEventException ex){
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }
    @ExceptionHandler(ConsultationException.class)
    public ResponseEntity<String> handleException(ConsultationException ex){
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }
}
