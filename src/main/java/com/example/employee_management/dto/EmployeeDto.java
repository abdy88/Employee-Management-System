package com.example.employee_management.dto;

import com.example.employee_management.entities.Department;
import com.example.employee_management.entities.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor


public class EmployeeDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Schema(type = "string", pattern = "dd-MM-yyyy", example = "05-07-2025")
    private LocalDate dateOfBirth;

    private Double salary;
    @NotNull(message = "Department is mandatory")
    @JsonIgnoreProperties({"departmentHead", "employees"})
    private Department department;

    private String address;

    private String role;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Schema(type = "string", pattern = "dd-MM-yyyy", example = "05-07-2025")
    private LocalDate joiningDate;

    private Double yearlyBonusPercentage;

    @JsonIgnoreProperties({"dateOfBirth", "salary", "department", "address", "joiningDate", "yearlyBonusPercentage", "reportingManager"})
    private Employee reportingManager;


}
