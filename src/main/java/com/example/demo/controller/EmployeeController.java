package com.example.demo.controller;


import com.example.demo.dto.EmployeeRequestDto;
import com.example.demo.dto.EmployeeResponseDto;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeservice;

    @PostMapping
    public EmployeeResponseDto addEmployee(@RequestBody EmployeeRequestDto requestDto) {
        return employeeservice.addEmployee(requestDto);
    }


    @GetMapping
    public List<Employee> getAllEmployee() {
        return employeeservice.getAllEmployee();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeservice.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDto updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDto requestDto) {
        return employeeservice.updateEmployee(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeservice.deleteEmployee(id);
        return "Employee deleted successfully!"; // bcz we return string here
    }

}
