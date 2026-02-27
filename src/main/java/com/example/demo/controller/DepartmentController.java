package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public Department addDepartement(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }

    @GetMapping
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartment();
    }
}
