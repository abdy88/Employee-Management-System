package com.example.employee_management.controller;


import com.example.employee_management.dto.EmployeeAnalyticsDto;
import com.example.employee_management.dto.ReportingChainDto;
import com.example.employee_management.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/employee-analytics")
    public ResponseEntity<EmployeeAnalyticsDto> getSummary() {
        return ResponseEntity.ok(analyticsService.getEmployeeSummary());
    }


    @GetMapping("/reporting-chain/{employeeId}")
    public ResponseEntity<ReportingChainDto> getReportingChain(@PathVariable Long employeeId) {
        ReportingChainDto dto = analyticsService.getReportingChain(employeeId);
        return ResponseEntity.ok(dto);
    }


}
