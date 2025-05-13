package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody Map<String, String> body) {
        User user = authService.register(
                body.get("username"),
                body.get("password"),
                body.get("email")
        );
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Map<String, String> body) {
        User user = authService.login(
                body.get("username"),
                body.get("password")
        );
        return ResponseEntity.ok(user);
    }
}