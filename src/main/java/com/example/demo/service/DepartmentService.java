package com.example.demo.service;


import com.example.demo.entity.Department;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo DepartmentRepo;


    public Department addDepartment(Department department) {
        Department department1=DepartmentRepo.findByDepartmentName(department.getDepartmentName());
        if (department1 == null) {
            return DepartmentRepo.save(department);
        }
        return department1;


    }

    public List<Department> getAllDepartment() {
        return DepartmentRepo.findAll();
    }
}
