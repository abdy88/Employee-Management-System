package com.example.employee_management.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private Double salary;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    private String address;
    private String role;
    private LocalDate joiningDate;
    private Double yearlyBonusPercentage;
    @ManyToOne
    @JoinColumn(name = "reporting_manager_id")
    private Employee reportingManager;


}
