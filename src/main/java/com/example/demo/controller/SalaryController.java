package com.example.demo.controller;

import com.example.demo.dto.SalaryResponseDto;
import com.example.demo.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salary")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService salaryService;

    @GetMapping("/{employeeId}")
    public SalaryResponseDto calculateSalary(
            @PathVariable Long employeeId,
            @RequestParam int year,
            @RequestParam int month
    ) {
        return salaryService.calculateSalary(employeeId, year, month);
    }
}