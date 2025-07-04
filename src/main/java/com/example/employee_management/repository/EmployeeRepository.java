package com.example.employee_management.repository;

import com.example.employee_management.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Query(value = "SELECT AVG(salary) FROM employee", nativeQuery = true)
    Double getAverageSalary();

    @Query(value = "SELECT AVG(yearly_bonus_percentage) FROM employee", nativeQuery = true)
    Double getAverageBonus();

    @Query(value = "SELECT MAX(salary) FROM employee", nativeQuery = true)
    Double getMaxSalary();

    @Query(value = "SELECT MIN(salary) FROM employee", nativeQuery = true)
    Double getMinSalary();

}
