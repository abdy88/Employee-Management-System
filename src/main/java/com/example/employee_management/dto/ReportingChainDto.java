package com.example.employee_management.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReportingChainDto {

    private String employee;
    private List<ManagerDto> chain = new ArrayList<>();

}
