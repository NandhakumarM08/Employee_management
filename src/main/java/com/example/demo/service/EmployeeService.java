package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.EmployeeRequestDto;
import com.example.demo.dto.EmployeeResponseDto;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.DepartmentRepo;
import com.example.demo.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    private EmployeeResponseDto mapToResponse(Employee employee) {

        DepartmentDto deptDTO = new DepartmentDto();
//        deptDTO.setDepartmentName(employee.getDepartment().getDepartmentName());

        EmployeeResponseDto response = new EmployeeResponseDto();
        response.setEmpId(employee.getEmpId());
        response.setName(employee.getName());
        response.setGender(employee.getGender());
        response.setPosition(employee.getPosition());
        response.setSalary(employee.getSalary());
        response.setPhone(employee.getPhone());
        response.setEmail(employee.getEmail());
        response.setDepartment(deptDTO);

        return response;
    }


    public EmployeeResponseDto addEmployee(EmployeeRequestDto requestDto) {


//        boolean exit = employeeRepo.findByEmail(requestDto.getEmail());
        if (employeeRepo.existsByEmail(requestDto.getEmail()))
        {
            throw new EmailAlreadyExistsException("Email already exists");

        }


            Department department = departmentRepo.findById(requestDto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department Not Found"));

            Employee employee = new Employee();
            employee.setName(requestDto.getName());
            employee.setGender(requestDto.getGender());
            employee.setDepartment(department);
            employee.setPosition(requestDto.getPosition());
            employee.setSalary(requestDto.getSalary());
            employee.setPhone(requestDto.getPhone());
            employee.setEmail(requestDto.getEmail());

            Employee savedEmployee = employeeRepo.save(employee);

            return mapToResponse(savedEmployee);
        }



    public List<Employee> getAllEmployee() {
        return  employeeRepo.findAll();
    }



    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

    }




    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto requestDto) {

        // 1️⃣ Check employee exists
        Employee existingEmployee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee id not found"));

        // 2️⃣ Check department exists
        Department department = departmentRepo.findById(requestDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        // 3️⃣ Update fields
        existingEmployee.setName(requestDto.getName());
        existingEmployee.setGender(requestDto.getGender());
        existingEmployee.setDepartment(department);  // 🔥 IMPORTANT
        existingEmployee.setPosition(requestDto.getPosition());
//        existingEmployee.setJoining(requestDto.getJoining());  // 🔥 IMPORTANT
        existingEmployee.setSalary(requestDto.getSalary());
        existingEmployee.setPhone(requestDto.getPhone());
        existingEmployee.setEmail(requestDto.getEmail());

        // 4️⃣ Save
        Employee savedEmployee = employeeRepo.save(existingEmployee);

        return mapToResponse(savedEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepo.delete(employee);
    }
}
