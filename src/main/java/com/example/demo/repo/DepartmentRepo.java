package com.example.demo.repo;

import com.example.demo.entity.Attendance;
import com.example.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DepartmentRepo extends JpaRepository<Department,Long> {
    Department findByDepartmentName(String departmentName);

}
