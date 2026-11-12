package com.hotoniel.employee_service.repository;

import com.hotoniel.employee_service.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByActiveTrue();
    Optional<Employee> findByIdAndActiveTrue(Long id);
    List<Employee> findByActiveTrueAndFirstNameContainingIgnoreCaseOrActiveTrueAndLastNameContainingIgnoreCase(String firstName, String lastName);
}