package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Department {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long departmentId;

    private String departmentName;

    @OneToMany(mappedBy = "department", cascade=CascadeType.ALL)
    private List<Employee> employees;
}
