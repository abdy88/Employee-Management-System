package com.example.employee_management.controller;

import com.example.employee_management.dto.DepartmentDto;
import com.example.employee_management.dto.DepartmentSummaryDto;
import com.example.employee_management.dto.PageableResponse;
import com.example.employee_management.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")

public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;


    @PostMapping
    ResponseEntity<DepartmentDto> createDepartment(@RequestBody @Valid DepartmentDto departmentDto) {

        DepartmentDto department = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(department, HttpStatus.CREATED);

    }


    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id,
                                                          @RequestBody @Valid DepartmentDto dto) {
        DepartmentDto department = departmentService.updateDepartment(id, dto);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<PageableResponse<DepartmentSummaryDto>> getDepartments(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize) {

        PageableResponse<DepartmentSummaryDto> response = departmentService.getAllDepartments(pageNumber, pageSize);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DepartmentSummaryDto> getDepartmentById(@PathVariable Long id) {
        DepartmentSummaryDto department = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }


    @GetMapping("/details/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentWithEmployees(
            @PathVariable Long id,
            @RequestParam(required = false) String expand) {

        DepartmentDto departmentDto;

        if ("employee".equalsIgnoreCase(expand)) {
            departmentDto = departmentService.getDepartmentWithEmployees(id);
        } else {
            departmentDto = departmentService.getDepartmentById(id);
        }

        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
        return new ResponseEntity<>("Department deleted Successfully", HttpStatus.OK);
    }


}
