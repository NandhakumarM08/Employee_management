package com.example.demo.entity;

import com.example.demo.dto.EmployeeRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name ="department_id")
    @JsonIgnore
    private Department department;


    private String position;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate joining;

    private Double salary;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Attendance> attendances;
}