package com.example.employee_management.dto;

import lombok.*;

import java.util.Map;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAnalyticsDto {


    private long totalEmployees;
    private double averageSalary;
    private double averageBonusPercentage;
    private double maxSalary;
    private double minSalary;
    private Map<String, Long> employeesPerDepartment;


}
