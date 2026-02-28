package com.example.demo.controller;

import com.example.demo.Auth.JwtUtil;
import com.example.demo.dto.LoginDto;
import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
//@RequiredArgsConstructor
public class AuthController {

    private final EmployeeRepo employeeRepo;
    private final JwtUtil jwtUtil;

    public AuthController(EmployeeRepo employeeRepo, JwtUtil jwtUtil) {
        this.employeeRepo = employeeRepo;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto dto) {

        Employee employee = employeeRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!employee.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(employee.getEmail());
    }
}