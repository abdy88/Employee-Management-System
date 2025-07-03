package com.example.employee_management.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate createdDate;
    @OneToOne
    @JoinColumn(name = "department_head_id")
    private Employee departmentHead;
    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();


}
