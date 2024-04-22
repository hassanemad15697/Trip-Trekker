package com.mentor.triptrekker.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        // Authentication logic here
        // For demo purposes, assume authentication is successful
        String token = "";
        return ResponseEntity.ok(token);
    }
}
