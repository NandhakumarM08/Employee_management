package com.example.demo.repo;

import com.example.demo.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepo extends JpaRepository<Leave,Long> {

    List<Leave> findByEmployeeEmpId(Long employeeId);
}
