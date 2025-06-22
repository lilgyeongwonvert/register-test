package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    // 회원가입 폼
    @GetMapping("/signup")
    public String signupForm() {
        return "signup"; // templates/signup.html
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, Model model) {
        boolean result = authService.register(user);
        if (result) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "이미 존재하는 아이디입니다.");
            return "signup";
        }
    }

    // 로그인 폼
    @GetMapping("/login")
    public String loginForm() {
        return "login"; // templates/login.html
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        boolean result = authService.login(username, password);
        if (result) {
            return "redirect:/";
        } else {
            model.addAttribute("error", "로그인 실패");
            return "login";
        }
    }
}