package com.example.demo.dto;

import lombok.Data;

@Data
public class EmployeeRequestDto {

    private String name;
    private String gender;
    private String position;
    private Double salary;
    private String phone;
    private String email;
    private Long departmentId;
}
