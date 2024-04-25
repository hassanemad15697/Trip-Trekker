package com.mentor.triptrekker.auth.controller;

import com.mentor.triptrekker.auth.request.AuthenticationRequest;
import com.mentor.triptrekker.auth.request.ChangePasswordRequest;
import com.mentor.triptrekker.auth.request.RegisterRequest;
import com.mentor.triptrekker.auth.response.AuthenticationResponse;
import com.mentor.triptrekker.auth.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

import static com.mentor.triptrekker.auth.config.security.SecurityConstants.BASE_AUTH_URL;

@RestController
@RequestMapping(BASE_AUTH_URL)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
    @PatchMapping("change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}
