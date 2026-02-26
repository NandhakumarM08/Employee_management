package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    private String name;
    private String gender;
    private String department;
    private String position;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate joining;

    private Double salary;
    private String phone;
    private String email;
}