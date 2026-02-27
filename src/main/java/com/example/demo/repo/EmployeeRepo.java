package com.example.demo.repo;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

//    boolean findByEmail(String email);

    boolean existsByEmail(String email);

//    Object getEmployeeById(Long id);

}
