package com.example.demo.service;

import com.example.demo.dto.AttendanceResponseDto;
import com.example.demo.entity.Attendance;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.AttendanceRepo;
import com.example.demo.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

    public AttendanceResponseDto checkIn(AttendanceResponseDto dto) {

        Employee employee = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Attendance existing =attendanceRepo.findByEmployeeEmpIdAndDate(dto.getEmployeeId(),LocalDate.now());

        if (existing!=null){
            throw new RuntimeException("Already checkin");
        }
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setDate(LocalDate.now());
        attendance.setCheckIn(LocalTime.now());

        Attendance saved = (Attendance) attendanceRepo.save(attendance);

        return mapToResponse(saved);
    }



    public AttendanceResponseDto checkOut(AttendanceResponseDto dto) {

        Attendance attendance = attendanceRepo
                .findByEmployeeEmpIdAndDate(dto.getEmployeeId(), LocalDate.now());

        if (attendance == null) {
            throw new ResourceNotFoundException("No check-in found for today");
        }

        if (attendance.getCheckOut() != null) {
            throw new RuntimeException("Already checked out");
        }

        attendance.setCheckOut(LocalTime.now());


        long minutes = Duration.between(
                attendance.getCheckIn(),
                attendance.getCheckOut()
        ).toMinutes();

        attendance.setWorkingHours(minutes);



        Attendance saved = (Attendance) attendanceRepo.save(attendance);

        return mapToResponse(saved);
    }



    public List<AttendanceResponseDto> getByEmployee(Long id) {
        return attendanceRepo.findByEmployeeEmpId(id)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private AttendanceResponseDto mapToResponse(Attendance a) {
        AttendanceResponseDto dto = new AttendanceResponseDto();
        dto.setId(a.getId());
        dto.setEmployeeId(a.getEmployee().getEmpId());
        dto.setEmployeeName(a.getEmployee().getName());
        dto.setDate(a.getDate());
        dto.setCheckIn(a.getCheckIn());
        dto.setCheckOut(a.getCheckOut());
        dto.setWorkingHours(a.getWorkingHours());


        return dto;
}
}