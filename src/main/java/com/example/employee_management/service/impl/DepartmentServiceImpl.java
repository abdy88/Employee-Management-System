package com.example.employee_management.service.impl;

import com.example.employee_management.dto.DepartmentDto;
import com.example.employee_management.dto.DepartmentSummaryDto;
import com.example.employee_management.dto.PageableResponse;
import com.example.employee_management.entities.Department;
import com.example.employee_management.entities.Employee;
import com.example.employee_management.exceptions.ResourceNotFoundException;
import com.example.employee_management.exceptions.ValidationException;
import com.example.employee_management.repository.DepartmentRepository;
import com.example.employee_management.repository.EmployeeRepository;
import com.example.employee_management.service.DepartmentService;
import com.example.employee_management.util.PageableResponseHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.employee_management.exceptions.InvalidOperationException;


import java.time.LocalDate;
import java.util.Optional;


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


        if (departmentDto.getDepartmentHead() != null && departmentDto.getDepartmentHead().getId() != null) {

            Employee head = employeeRepository.findById(departmentDto.getDepartmentHead().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department head not found"));
            Long newHeadId = departmentDto.getDepartmentHead().getId();
            // Check if the employee is already department head of a *different* department
            Optional<Department> existing = departmentRepository.findByDepartmentHeadId(newHeadId);

            if (existing.isPresent()) {
                throw new ValidationException("This employee is already a department head of another department.");
            }}

        departmentDto.setCreatedDate(LocalDate.now());
        Department savedEntity = departmentRepository.save(mapper.map(departmentDto, Department.class));
        return mapper.map(savedEntity, DepartmentDto.class);
    }

    @Override
    public DepartmentDto updateDepartment(Long id, DepartmentDto dto) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        // Update fields

        dept.setName(dto.getName() != null ? dto.getName() : dept.getName());


        if (dto.getDepartmentHead() != null && dto.getDepartmentHead().getId() != null) {

            Employee head = employeeRepository.findById(dto.getDepartmentHead().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department head not found"));
            Long newHeadId = dto.getDepartmentHead().getId();
            // Check if the employee is already department head of a *different* department
            Optional<Department> existing = departmentRepository.findByDepartmentHeadId(newHeadId);

            if (existing.isPresent() && !existing.get().getId().equals(id)) {
                throw new ValidationException("This employee is already a department head of another department.");
            }

            dept.setDepartmentHead(head);
        }



        dept = departmentRepository.save(dept);
        return mapper.map(dept, DepartmentDto.class);
    }


//    @Override
//    public Page<DepartmentSummaryDto> getAllDepartments(int pageNumber, int pageSize) {
//        Pageable p = PageRequest.of(pageNumber, pageSize);
//        Page<Department> departments = departmentRepository.findAll(p);
//        return departments.map(dept -> mapper.map(dept, DepartmentSummaryDto.class));
//    }
//


    @Override
    public PageableResponse<DepartmentSummaryDto> getAllDepartments(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Department> departmentPage = departmentRepository.findAll(pageable);

        // Map each Department to DepartmentSummaryDto
        Page<DepartmentSummaryDto> dtoPage = departmentPage.map(dept ->
                mapper.map(dept, DepartmentSummaryDto.class)
        );

        // Convert to custom response
        return PageableResponseHelper.toPageableResponse(dtoPage);
    }


//    @Override
//    public DepartmentDto getDepartmentById(Long id) {
//        Department dept = departmentRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Department not found"));
//        return mapper.map(dept, DepartmentDto.class);
//    }

    @Override
    public DepartmentSummaryDto getDepartmentById(Long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + id));
        return mapper.map(dept, DepartmentSummaryDto.class);
    }



    @Override
    public DepartmentDto getDepartmentWithEmployees(Long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + id));

        return mapper.map(dept, DepartmentDto.class);
    }


    @Override
    public void deleteDepartmentById(Long Id) {

        Department dept = departmentRepository.findById(Id).orElseThrow(() -> new RuntimeException("Department not found"));

        if (!dept.getEmployees().isEmpty()) {
            throw new InvalidOperationException("Cannot delete department with assigned employees.");
        }


        departmentRepository.delete(dept);

    }
}
