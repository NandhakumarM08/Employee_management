package com.example.demo.service;

import com.example.demo.dto.SalaryResponseDto;
import com.example.demo.entity.Attendance;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.AttendanceRepo;
import com.example.demo.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private AttendanceRepo attendanceRepo;
    public SalaryResponseDto calculateSalary(Long employeeId, int year, int month) {

        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<Attendance> attendances =
                attendanceRepo.findByEmployeeEmpIdAndDateBetween(employeeId, start, end);

        long totalMinutes = attendances.stream()
                .filter(a -> a.getWorkingHours() != null)
                .mapToLong(Attendance::getWorkingHours)
                .sum();

        // Assume 160 working hours per month (160 * 60 minutes)
        double hourlyRate = employee.getSalary() / 160.0;

        double workedHours = totalMinutes / 60.0;

        double calculatedSalary = hourlyRate * workedHours;

        SalaryResponseDto dto = new SalaryResponseDto();
        dto.setEmployeeId(employee.getEmpId());
        dto.setEmployeeName(employee.getName());
        dto.setMonthlySalary(employee.getSalary());
        dto.setTotalWorkingMinutes(totalMinutes);
        dto.setCalculatedSalary(calculatedSalary);

        return dto;
    }
}
