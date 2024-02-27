package com.example.assignment.controller;

import com.example.assignment.annotation.RateLimited;
import com.example.assignment.dto.response.Response;
import com.example.assignment.service.DataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/concurrent-data")
public class ConcurrentDataController {

    private final DataService dataService;

    public ConcurrentDataController(DataService dataService) {
        this.dataService = dataService;
    }

    @RateLimited(key = "fetchDataConcurrently", tokens = 10, timeWindowInSeconds = 60)
    @GetMapping("/fetch")
    public ResponseEntity<Response> fetchDataConcurrently() {
        try {
            String result = dataService.fetchDataConcurrently();
            return ResponseEntity.ok(new Response(result));
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(new Response(e.getMessage()));
        }
    }
}

