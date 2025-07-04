package com.example.employee_management.controller;

import com.example.employee_management.dto.EmployeeDto;
import com.example.employee_management.dto.EmployeeSummaryDto;
import com.example.employee_management.dto.PageableResponse;
import com.example.employee_management.exceptions.ValidationException;
import com.example.employee_management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee")

public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto dto) {
        return new ResponseEntity<>(employeeService.createEmployee(dto), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<PageableResponse<EmployeeDto>> getAllEmployees(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok(employeeService.getAllEmployees(pageable));
    }


    @GetMapping("/employeeSummary")
    public ResponseEntity<PageableResponse<EmployeeSummaryDto>> getEmployeeNamesAndIds(
            @RequestParam(required = true) Boolean lookup,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize) {
        if (lookup != null && lookup) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            return ResponseEntity.ok(employeeService.getEmployeeNameAndId(pageable));
        } else {
            throw new ValidationException("'lookup=true' is required to fetch employee summary");
        }

    }


    @PatchMapping("/{employeeId}/department/{newDepartmentId}")
    public ResponseEntity<EmployeeDto> changeDepartment(
            @PathVariable Long employeeId,
            @PathVariable Long newDepartmentId) {

        EmployeeDto updatedEmployee = employeeService.changeDepartment(employeeId, newDepartmentId);
        return ResponseEntity.ok(updatedEmployee);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeDto employeeDto
    ) {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(id, employeeDto);
        return ResponseEntity.ok(updatedEmployee);
    }


}
