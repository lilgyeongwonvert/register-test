package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        boolean result = authService.register(user);
        if (result) {
            return ResponseEntity.ok(Map.of("result", "success"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("result", "fail", "error", "이미 존재하는 아이디입니다."));
        }
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> params) {
        boolean result = authService.login(params.get("username"), params.get("password"));
        if (result) {
            return ResponseEntity.ok(Map.of("result", "success"));
        } else {
            return ResponseEntity.status(401).body(Map.of("result", "fail", "error", "로그인 실패"));
        }
    }
}
