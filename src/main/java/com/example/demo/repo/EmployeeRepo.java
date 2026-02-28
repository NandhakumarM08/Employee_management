package com.example.demo.repo;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployeeRepo extends JpaRepository<Employee, Long> {

//    boolean findByEmail(String email);

    boolean existsByEmail(String email);

//    <T> ScopedValue<T> findByEmail(String email);

//    Object getEmployeeById(Long id);
    Optional<Employee> findByEmail(String email);

}
