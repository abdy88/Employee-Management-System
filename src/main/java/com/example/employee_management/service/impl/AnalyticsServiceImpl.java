package com.example.employee_management.service.impl;

import com.example.employee_management.dto.EmployeeAnalyticsDto;
import com.example.employee_management.dto.ManagerDto;
import com.example.employee_management.dto.ReportingChainDto;
import com.example.employee_management.entities.Employee;
import com.example.employee_management.exceptions.ResourceNotFoundException;
import com.example.employee_management.repository.DepartmentRepository;
import com.example.employee_management.repository.EmployeeRepository;
import com.example.employee_management.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;



    @Override
    public EmployeeAnalyticsDto getEmployeeSummary() {
        EmployeeAnalyticsDto dto = new EmployeeAnalyticsDto();

        dto.setTotalEmployees(employeeRepository.count());
        dto.setAverageSalary(employeeRepository.getAverageSalary());
        dto.setAverageBonusPercentage(employeeRepository.getAverageBonus());
        dto.setMaxSalary(employeeRepository.getMaxSalary());
        dto.setMinSalary(employeeRepository.getMinSalary());

        Map<String, Long> empPerDept = new HashMap<>();
        List<Object[]> result = departmentRepository.countEmployeesByDepartment();
        for (Object[] row : result) {
            String deptName = (String) row[0];
            Long count = (Long) row[1];
            empPerDept.put(deptName, count);
        }

        dto.setEmployeesPerDepartment(empPerDept);

        return dto;
    }




    public ReportingChainDto getReportingChain(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        ReportingChainDto dto = new ReportingChainDto();
        dto.setEmployee(employee.getName());

        List<ManagerDto> chain = new ArrayList<>();
        Employee current = employee.getReportingManager();

        while (current != null) {
            ManagerDto managerDto = new ManagerDto();
            managerDto.setId(current.getId());
            managerDto.setName(current.getName());
            managerDto.setRole(current.getRole());

            chain.add(managerDto);
            current = current.getReportingManager();
        }

        dto.setChain(chain);
        return dto;
    }







}