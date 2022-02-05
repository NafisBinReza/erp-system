package com.rezso.backend.repository;

import com.rezso.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findEmployeeByJobTitle(String jobTitle);
}
