package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveResponseDto {

    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}
