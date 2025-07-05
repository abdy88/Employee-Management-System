package com.example.employee_management.dto;

import com.example.employee_management.entities.Employee;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDto {


    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    private LocalDate createdDate;
    @Getter
    @JsonIgnoreProperties({"dateOfBirth", "salary", "department", "address", "joiningDate", "yearlyBonusPercentage", "reportingManager"})
    private Employee departmentHead;
    @JsonIgnoreProperties({"dateOfBirth", "salary", "department", "address", "joiningDate", "yearlyBonusPercentage", "reportingManager"})
    private List<Employee> employees = new ArrayList<>();

}
