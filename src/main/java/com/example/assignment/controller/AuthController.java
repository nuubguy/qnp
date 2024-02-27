package com.example.assignment.controller;

import com.example.assignment.dto.response.Response;
import com.example.assignment.util.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/generate-token")
    public ResponseEntity<?> generateToken() {
        String mockUser = "user123";
        ResponseEntity.ok().body(new Response(Jwt.generateToken(mockUser)));
        return ResponseEntity.ok().body(new Response(Jwt.generateToken(mockUser)));
    }
}
