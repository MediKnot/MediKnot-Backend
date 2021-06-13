package com.hack.azure.mediknot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/check-server")
    public ResponseEntity<HashMap<String, Object>> checkServer(){
        HashMap<String, Object> response = new HashMap<>();
        response.put("Message", "Server is up");
        response.put("Status", true);
        return ResponseEntity.ok(response);
    }
}
