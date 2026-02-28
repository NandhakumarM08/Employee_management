package com.example.demo.repo;

import com.example.demo.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepo extends JpaRepository<Attendance,Long> {

//    Attendance findByEmployeeEmpIdAndDate(Long employeeId, LocalDate now);

    List<Attendance> findByEmployeeEmpId(Long employeeId);
    Attendance findByEmployeeEmpIdAndDate(Long employeeId, LocalDate date);

    List<Attendance> findByEmployeeEmpIdAndDateBetween(
            Long employeeId,
            LocalDate start,
            LocalDate end
    );

}
