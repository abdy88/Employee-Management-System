package com.example.employee_management.service.impl;

import com.example.employee_management.dto.EmployeeDto;
import com.example.employee_management.dto.EmployeeSummaryDto;
import com.example.employee_management.dto.PageableResponse;
import com.example.employee_management.entities.Department;
import com.example.employee_management.entities.Employee;
import com.example.employee_management.exceptions.ResourceNotFoundException;
import com.example.employee_management.exceptions.ValidationException;
import com.example.employee_management.repository.DepartmentRepository;
import com.example.employee_management.repository.EmployeeRepository;
import com.example.employee_management.service.EmployeeService;
import com.example.employee_management.util.PageableResponseHelper;
import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper mapper;


    @Override
    public EmployeeDto createEmployee(EmployeeDto dto) {


        if (dto.getReportingManager() == null || dto.getReportingManager().getId() == null) {
            if (dto.getRole() == null || !dto.getRole().equalsIgnoreCase("CEO")) {
                throw new ValidationException("Reporting manager required. Only employee with the role 'CEO' can have no reporting manager.");
            }
        }

        if (dto.getDepartment() != null && dto.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(dto.getDepartment().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        }


        if (dto.getReportingManager() != null && dto.getReportingManager().getId() != null) {
            Employee manager = employeeRepository.findById(dto.getReportingManager().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Reporting manager not found"));
        }

        Employee employee = mapper.map(dto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return mapper.map(savedEmployee,EmployeeDto.class);



    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        emp.setName(dto.getName() != null ? dto.getName() : emp.getName());
        emp.setAddress(dto.getAddress() != null ? dto.getAddress() : emp.getAddress());
        emp.setDateOfBirth(dto.getDateOfBirth() != null ? dto.getDateOfBirth() : emp.getDateOfBirth());
        emp.setJoiningDate(dto.getJoiningDate() != null ? dto.getJoiningDate() : emp.getJoiningDate());
        emp.setSalary(dto.getSalary() != null ? dto.getSalary() : emp.getSalary());
        emp.setRole(dto.getRole() != null ? dto.getRole() : emp.getRole());
        emp.setYearlyBonusPercentage(dto.getYearlyBonusPercentage() != null ? dto.getYearlyBonusPercentage() : emp.getYearlyBonusPercentage());

        emp.setDepartment(
                dto.getDepartment().getId() != null
                        ? departmentRepository.findById(dto.getDepartment().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Department not found"))
                        : emp.getDepartment()
        );

        emp.setReportingManager(
                dto.getReportingManager().getId() != null
                        ? employeeRepository.findById(dto.getReportingManager().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Reporting Manager not found"))
                        : emp.getReportingManager()
        );

        return mapper.map(employeeRepository.save(emp), EmployeeDto.class);
    }



    @Override
    public PageableResponse<EmployeeDto> getAllEmployees(Pageable pageable) {
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        Page<EmployeeDto> dtoPage = employeePage.map(emp -> mapper.map(emp, EmployeeDto.class));
        return PageableResponseHelper.toPageableResponse(dtoPage);
    }





    @Override
    public PageableResponse<EmployeeSummaryDto> getEmployeeNameAndId(Pageable pageable) {
        Page<Employee> employeePage =employeeRepository.findAll(pageable);
        Page<EmployeeSummaryDto> dtoPage = employeePage.map(emp -> mapper.map(emp, EmployeeSummaryDto.class));
        return PageableResponseHelper.toPageableResponse(dtoPage);
    }




    @Override
    public EmployeeDto changeDepartment(Long employeeId, Long newDepartmentId) {
        // Find the employee
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));

        // Find the new department
        Department newDepartment = departmentRepository.findById(newDepartmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + newDepartmentId));

        // Set the new department
        employee.setDepartment(newDepartment);

        // Save and return updated employee as DTO
        return mapper.map(employeeRepository.save(employee), EmployeeDto.class);
    }








}
