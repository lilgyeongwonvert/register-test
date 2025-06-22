package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    // 회원가입
    public boolean register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return false; // 이미 존재
        }
        userRepository.save(user);
        return true;
    }

    // 로그인
    public boolean login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).isPresent();
    }
}
