package com.example.demo.controller;

import com.example.demo.dto.AttendanceResponseDto;
import com.example.demo.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/checkin")
    public AttendanceResponseDto checkIn(@RequestBody AttendanceResponseDto dto){
        return attendanceService.checkIn(dto);
    }

//    @SneakyThrows
    @PutMapping("/checkout/{id}")
    public AttendanceResponseDto checkOut(@RequestBody AttendanceResponseDto dto) throws Throwable {
        return attendanceService.checkOut(dto);
    }

    @GetMapping("/employee/{id}")
    public List<AttendanceResponseDto> getByEmployee(@PathVariable Long id) {
        return attendanceService.getByEmployee(id);
    }
}
