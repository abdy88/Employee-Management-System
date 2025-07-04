package com.example.employee_management.service;

import com.example.employee_management.dto.EmployeeSummaryDto;
import com.example.employee_management.dto.PageableResponse;
import org.springframework.data.domain.Page;
import com.example.employee_management.dto.EmployeeDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto dto);

    EmployeeDto updateEmployee(Long id, EmployeeDto dto);

    PageableResponse<EmployeeDto> getAllEmployees(Pageable pageable);

    PageableResponse<EmployeeSummaryDto> getEmployeeNameAndId(Pageable pageable);

    EmployeeDto changeDepartment(Long employeeId, Long newDepartmentId);


}