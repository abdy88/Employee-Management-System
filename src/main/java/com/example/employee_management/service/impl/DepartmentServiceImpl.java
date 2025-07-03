package com.example.employee_management.service.impl;

import com.example.employee_management.dto.DepartmentDto;
import com.example.employee_management.dto.DepartmentSummaryDto;
import com.example.employee_management.entities.Department;
import com.example.employee_management.repository.DepartmentRepository;
import com.example.employee_management.repository.EmployeeRepository;
import com.example.employee_management.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper mapper;


    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {

        departmentDto.setCreatedDate(LocalDate.now());
        Department savedEntity = departmentRepository.save(mapper.map(departmentDto, Department.class));
        return mapper.map(savedEntity, DepartmentDto.class);
    }

    @Override
    public DepartmentDto updateDepartment(Long id, DepartmentDto dto) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // Update fields

        dept.setName(dto.getName() != null ? dto.getName() : dept.getName());


        if (dto.getDepartmentHead().getId() != null) {
            dept.setDepartmentHead(employeeRepository.findById(dto.getDepartmentHead().getId())
                    .orElseThrow(() -> new RuntimeException("Department head not found")));
        }

        dept = departmentRepository.save(dept);
        return mapper.map(dept, DepartmentDto.class);
    }


    @Override
    public Page<DepartmentSummaryDto> getAllDepartments(int pageNumber, int pageSize) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<Department> departments = departmentRepository.findAll(p);
        return departments.map(dept -> mapper.map(dept, DepartmentSummaryDto.class));
    }


    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return mapper.map(dept, DepartmentDto.class);
    }


    @Override
    public DepartmentDto getDepartmentWithEmployees(Long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        return mapper.map(dept, DepartmentDto.class);
    }


    @Override
    public void deleteDepartmentById(Long Id) {

        Department dept = departmentRepository.findById(Id).orElseThrow(() -> new RuntimeException("Department not found"));

        if (!dept.getEmployees().isEmpty()) {
            throw new RuntimeException("Cannot delete department with assigned employees.");
        }

        departmentRepository.delete(dept);

    }
}
