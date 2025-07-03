package com.example.employee_management.controller;



import com.example.employee_management.dto.DepartmentDto;
import com.example.employee_management.dto.DepartmentSummaryDto;
import com.example.employee_management.service.DepartmentService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")

public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;


    @PostMapping
    ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {

        DepartmentDto department = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(department, HttpStatus.CREATED);

    }


    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id,
                                                          @RequestBody DepartmentDto dto) {
        DepartmentDto department = departmentService.updateDepartment(id, dto);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<Page<DepartmentSummaryDto>> getAllDepartments(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                                        @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize) {
        Page<DepartmentSummaryDto> departments = departmentService.getAllDepartments(pageNumber, pageSize);
        return new ResponseEntity<>(departments, HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        DepartmentDto department = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }


    @GetMapping("/details/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentWithEmployees(@PathVariable Long id) {

        DepartmentDto departmentWithEmployees = departmentService.getDepartmentWithEmployees(id);
        return new ResponseEntity<>(departmentWithEmployees, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
        return new ResponseEntity<>("Department deleted Successfully", HttpStatus.OK);
    }


}
