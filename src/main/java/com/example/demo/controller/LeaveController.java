package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leave")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    @PostMapping("/apply")
    public <LeaveRequestDto> LeaveResponseDto applyLeave(@RequestBody LeaveResponseDto dto) {
        return leaveService.applyLeave(dto);
    }

    @PutMapping("/approve/{id}")
    public LeaveResponseDto approveLeave(@PathVariable Long id) {
        return leaveService.approveLeave(id);
    }

    @PutMapping("/reject/{id}")
    public LeaveResponseDto rejectLeave(@PathVariable Long id) {
        return leaveService.rejectLeave(id);
    }

    @GetMapping("/employee/{id}")
    public List<LeaveResponseDto> getByEmployee(@PathVariable Long id) {
        return leaveService.getByEmployee(id);
    }
}