package com.example.demo.dto;


import lombok.Data;

@Data
public class SalaryResponseDto {
    private Long employeeId;
    private String employeeName;
    private Double monthlySalary;
    private Long totalWorkingMinutes;
    private Double calculatedSalary;
}
