package com.example.employee_management.service;

import com.example.employee_management.dto.DepartmentDto;
import com.example.employee_management.dto.DepartmentSummaryDto;
import com.example.employee_management.dto.PageableResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {


    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto);

    PageableResponse<DepartmentSummaryDto> getAllDepartments(int pageNumber, int pageSize);

    DepartmentSummaryDto getDepartmentById(Long Id);

    DepartmentDto getDepartmentWithEmployees(Long Id);

    public void deleteDepartmentById(Long Id);


}
