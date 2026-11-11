package com.hotoniel.employee_service.mapper;

import com.hotoniel.employee_service.domain.entity.Employee;
import com.hotoniel.employee_service.dto.request.EmployeeRequest;
import com.hotoniel.employee_service.dto.response.EmployeeResponse;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest request) {
        if (request == null) return null;
        return Employee.builder()
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .lastName(request.getLastName())
                .maternalLastName(request.getMaternalLastName())
                .age(request.getAge())
                .gender(request.getGender())
                .birthDate(request.getBirthDate())
                .position(request.getPosition())
                .active(request.getActive() != null ? request.getActive() : true)
                .build();
    }

    public EmployeeResponse toResponse(Employee employee) {
        if (employee == null) return null;
        return EmployeeResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .middleName(employee.getMiddleName())
                .lastName(employee.getLastName())
                .maternalLastName(employee.getMaternalLastName())
                .age(employee.getAge())
                .gender(employee.getGender())
                .birthDate(employee.getBirthDate())
                .position(employee.getPosition())
                .createdAt(employee.getCreatedAt())
                .active(employee.getActive())
                .build();
    }

    public void updateEntity(Employee employee, EmployeeRequest request) {
        employee.setFirstName(request.getFirstName());
        employee.setMiddleName(request.getMiddleName());
        employee.setLastName(request.getLastName());
        employee.setMaternalLastName(request.getMaternalLastName());
        employee.setAge(request.getAge());
        employee.setGender(request.getGender());
        employee.setBirthDate(request.getBirthDate());
        employee.setPosition(request.getPosition());
        if (request.getActive() != null) {
            employee.setActive(request.getActive());
        }
    }
}
