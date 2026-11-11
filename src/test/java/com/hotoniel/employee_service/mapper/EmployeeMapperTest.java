package com.hotoniel.employee_service.mapper;

import com.hotoniel.employee_service.domain.entity.Employee;
import com.hotoniel.employee_service.domain.enums.Gender;
import com.hotoniel.employee_service.dto.request.EmployeeRequest;
import com.hotoniel.employee_service.dto.response.EmployeeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeMapperTest {

    private EmployeeMapper employeeMapper;

    @BeforeEach
    void setUp() {
        employeeMapper = new EmployeeMapper();
    }

    @Test
    void toEntity_ShouldMapCorrectly() {
        EmployeeRequest request = EmployeeRequest.builder()
                .firstName("John")
                .middleName("Michael")
                .lastName("Doe")
                .maternalLastName("Smith")
                .age(30)
                .gender(Gender.MALE)
                .birthDate(LocalDate.of(1994, 5, 12))
                .position("Developer")
                .active(true)
                .build();

        Employee employee = employeeMapper.toEntity(request);

        assertThat(employee).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo("John");
        assertThat(employee.getMaternalLastName()).isEqualTo("Smith");
    }

    @Test
    void toEntity_ShouldReturnNull_WhenRequestIsNull() {
        assertThat(employeeMapper.toEntity(null)).isNull();
    }

    @Test
    void toResponse_ShouldMapCorrectly() {
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .maternalLastName("Smith")
                .age(30)
                .gender(Gender.MALE)
                .birthDate(LocalDate.of(1994, 5, 12))
                .position("Developer")
                .createdAt(LocalDateTime.now())
                .active(true)
                .build();

        EmployeeResponse response = employeeMapper.toResponse(employee);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getFirstName()).isEqualTo("John");
    }

    @Test
    void toResponse_ShouldReturnNull_WhenEmployeeIsNull() {
        assertThat(employeeMapper.toResponse(null)).isNull();
    }

    @Test
    void updateEntity_ShouldUpdateFields() {
        Employee employee = new Employee();
        EmployeeRequest request = EmployeeRequest.builder()
                .firstName("Jane")
                .middleName("A.")
                .lastName("Smith")
                .maternalLastName("Johnson")
                .age(25)
                .gender(Gender.FEMALE)
                .birthDate(LocalDate.of(1999, 1, 1))
                .position("Tester")
                .active(false)
                .build();

        employeeMapper.updateEntity(employee, request);

        assertThat(employee.getFirstName()).isEqualTo("Jane");
        assertThat(employee.getActive()).isFalse();
    }
}