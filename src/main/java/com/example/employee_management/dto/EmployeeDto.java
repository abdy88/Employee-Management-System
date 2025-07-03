package com.example.employee_management.dto;

import com.example.employee_management.common.view.Views;
import com.example.employee_management.entities.Department;
import com.example.employee_management.entities.Employee;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor


public class EmployeeDto {


    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private Double salary;

    private Department department;

    private String address;

    private String role;

    private LocalDate joiningDate;

    private Double yearlyBonusPercentage;

    private Employee reportingManager;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Double getYearlyBonusPercentage() {
        return yearlyBonusPercentage;
    }

    public void setYearlyBonusPercentage(Double yearlyBonusPercentage) {
        this.yearlyBonusPercentage = yearlyBonusPercentage;
    }

    public Employee getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(Employee reportingManager) {
        this.reportingManager = reportingManager;
    }
}
