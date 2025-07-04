package com.example.employee_management.service;
import com.example.employee_management.dto.EmployeeAnalyticsDto;
import com.example.employee_management.dto.ReportingChainDto;
import org.springframework.stereotype.Service;


@Service
public interface AnalyticsService {


    EmployeeAnalyticsDto getEmployeeSummary();

    ReportingChainDto getReportingChain(Long employeeId);




}
