package com.example.employee_management.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class ManagerDto {

    private Long id;
    private String name;
    private String role;


}
