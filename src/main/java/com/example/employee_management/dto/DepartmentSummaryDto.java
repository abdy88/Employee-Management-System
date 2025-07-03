package com.example.employee_management.dto;

import com.example.employee_management.entities.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;



public class DepartmentSummaryDto extends DepartmentDto{

    @JsonIgnoreProperties({"dateOfBirth", "salary", "department", "address", "role", "joiningDate", "yearlyBonusPercentage", "reportingManager"})
    private Employee departmentHead;

    @JsonIgnoreProperties({"dateOfBirth", "salary", "department", "address", "role", "joiningDate", "yearlyBonusPercentage", "reportingManager"})
    private List<Employee> employees = new ArrayList<>();

}
