package com.example.employee_management.dto;

import com.example.employee_management.entities.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor


public class DepartmentDto {


    private Long id;
    private String name;
    private LocalDate createdDate;

    @JsonIgnoreProperties({"dateOfBirth", "salary", "department", "address", "joiningDate", "yearlyBonusPercentage", "reportingManager"})
    private Employee departmentHead;
    @JsonIgnoreProperties({"dateOfBirth", "salary", "department", "address", "joiningDate", "yearlyBonusPercentage", "reportingManager"})
    private List<Employee> employees = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Employee getDepartmentHead() {
        return departmentHead;
    }

    public List<Employee> getEmployees() {
        return employees;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setDepartmentHead(Employee departmentHead) {
        this.departmentHead = departmentHead;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
