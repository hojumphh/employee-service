package com.hotoniel.employee_service.service;

import com.hotoniel.employee_service.dto.request.EmployeeRequest;
import com.hotoniel.employee_service.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse getEmployeeById(Long id);
    List<EmployeeResponse> saveEmployees(List<EmployeeRequest> requests); // Soporte para uno o varios[cite: 1]
    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);
    void deleteEmployee(Long id);
    List<EmployeeResponse> searchEmployeesByName(String name);
}
