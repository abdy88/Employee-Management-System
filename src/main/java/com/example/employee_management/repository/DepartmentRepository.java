package com.example.employee_management.repository;

import com.example.employee_management.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {


    Optional<Department> findByDepartmentHeadId(Long employeeId);


    @Query(value = "SELECT d.name, COUNT(e.id) AS employee_count " +
            "FROM department d " +
            "LEFT JOIN employee e ON e.department_id = d.id " +
            "GROUP BY d.name " +
            "ORDER BY employee_count DESC", nativeQuery = true)
    List<Object[]> countEmployeesByDepartment();

}
