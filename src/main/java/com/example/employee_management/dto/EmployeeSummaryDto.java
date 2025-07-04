package com.example.employee_management.dto;

import com.example.employee_management.entities.Department;
import com.example.employee_management.entities.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

public class EmployeeSummaryDto extends EmployeeDto {
    @JsonIgnore
    private LocalDate dateOfBirth;
    @JsonIgnore
    private Double salary;
    @JsonIgnore
    private Department department;
    @JsonIgnore
    private String address;
    @JsonIgnore
    private String role;
    @JsonIgnore
    private LocalDate joiningDate;
    @JsonIgnore
    private Double yearlyBonusPercentage;
    @JsonIgnore
    private Employee reportingManager;


}
