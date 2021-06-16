package com.hack.azure.mediknot.controller;

import com.hack.azure.mediknot.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DoctorException.class)
    public ResponseEntity<?> handleException(DoctorException ex){
        HashMap<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(PatientException.class)
    public ResponseEntity<?> handleException(PatientException ex){
        HashMap<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(DiseaseException.class)
    public ResponseEntity<?> handleException(DiseaseException ex){
        HashMap<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(MedicineException.class)
    public ResponseEntity<?> handleException(MedicineException ex){
        HashMap<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(MedicalEventException.class)
    public ResponseEntity<?> handleException(MedicalEventException ex){
        HashMap<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(ConsultationException.class)
    public ResponseEntity<?> handleException(ConsultationException ex){
        HashMap<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
    }
    @ExceptionHandler(PrescriptionException.class)
    public ResponseEntity<?> handleException(PrescriptionException ex){
        HashMap<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
    }
}
