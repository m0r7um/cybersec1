package org.example.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.demo.dto.AuthRequest;
import org.example.demo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody @Valid AuthRequest request) {
        return authService.register(request);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody @Valid AuthRequest request) {
        return authService.login(request);
    }
}
