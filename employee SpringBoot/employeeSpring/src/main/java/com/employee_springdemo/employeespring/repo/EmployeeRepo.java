package com.employee_springdemo.employeespring.repo;

import com.employee_springdemo.employeespring.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo  extends JpaRepository<Employee,Long> {


    Optional<Employee> findByName( String name);
    Optional<List<Employee>>findByNameContainingIgnoreCase(String name);


}
