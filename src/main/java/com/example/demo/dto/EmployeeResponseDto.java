package com.example.demo.dto;

import lombok.Data;

@Data
public class EmployeeResponseDto  {
    private Long empId;
    private String name;
    private String gender;
    private String position;
    private Double salary;
    private String phone;
    private String email;
    private DepartmentDto department;
}
