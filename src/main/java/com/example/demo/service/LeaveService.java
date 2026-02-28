package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repo.*;
import com.example.demo.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveService {

    private final LeaveRepo leaveRepo;
    private final EmployeeRepo employeeRepo;

    public LeaveResponseDto applyLeave(LeaveResponseDto dto) {

        Employee employee = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Leave leave = new Leave();
        leave.setEmployee(employee);
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setReason(dto.getReason());
        leave.setStatus(LeaveStatus.PENDING);

        Leave saved = leaveRepo.save(leave);

        return mapToResponse(saved);
    }

    public LeaveResponseDto approveLeave(Long leaveId) {

        Leave leave = leaveRepo.findById(leaveId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found"));

        leave.setStatus(LeaveStatus.APPROVED);

        return mapToResponse(leaveRepo.save(leave));
    }

    public LeaveResponseDto rejectLeave(Long leaveId) {

        Leave leave = leaveRepo.findById(leaveId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found"));

        leave.setStatus(LeaveStatus.REJECTED);

        return mapToResponse(leaveRepo.save(leave));
    }

    public List<LeaveResponseDto> getByEmployee(Long employeeId) {
        return leaveRepo.findByEmployeeEmpId(employeeId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private LeaveResponseDto mapToResponse(Leave leave) {
        LeaveResponseDto dto = new LeaveResponseDto();
//        dto.setId(leave.getId());
        dto.setEmployeeId(leave.getEmployee().getEmpId());
//        dto.setEmployeeName(leave.getEmployee().getName());
        dto.setStartDate(leave.getStartDate());
        dto.setEndDate(leave.getEndDate());
        dto.setReason(leave.getReason());
//        dto.setStatus(leave.getStatus().name());
        return dto;
    }
}