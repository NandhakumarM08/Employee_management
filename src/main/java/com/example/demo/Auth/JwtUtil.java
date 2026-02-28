package com.example.demo.Auth;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String generateToken(String email) {
        return "dummy-token-for-" + email;
    }
}