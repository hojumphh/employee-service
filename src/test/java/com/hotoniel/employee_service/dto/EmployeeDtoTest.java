package com.hotoniel.employee_service;

import com.hotoniel.employee_service.domain.entity.Employee;
import com.hotoniel.employee_service.domain.enums.Gender;
import com.hotoniel.employee_service.dto.request.EmployeeRequest;
import com.hotoniel.employee_service.dto.response.EmployeeResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeDtoTest {

    @Test
    void testEmployeeRequestGettersAndSetters() {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("John");
        request.setMiddleName("Michael");
        request.setLastName("Doe");
        request.setMaternalLastName("Smith");
        request.setAge(30);
        request.setGender(Gender.MALE);
        request.setBirthDate(LocalDate.of(1994, 5, 12));
        request.setPosition("Senior Developer");
        request.setActive(true);

        assertThat(request.getFirstName()).isEqualTo("John");
        assertThat(request.getMiddleName()).isEqualTo("Michael");
        assertThat(request.getLastName()).isEqualTo("Doe");
        assertThat(request.getMaternalLastName()).isEqualTo("Smith");
        assertThat(request.getAge()).isEqualTo(30);
        assertThat(request.getGender()).isEqualTo(Gender.MALE);
        assertThat(request.getBirthDate()).isEqualTo(LocalDate.of(1994, 5, 12));
        assertThat(request.getPosition()).isEqualTo("Senior Developer");
        assertThat(request.getActive()).isTrue();
    }

    @Test
    void testEmployeeResponseBuilder() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate nowDate = LocalDate.now();
        EmployeeResponse response = EmployeeResponse.builder()
                .id(1L)
                .firstName("John")
                .middleName("")
                .lastName("Doe")
                .maternalLastName("")
                .gender(Gender.FEMALE)
                .birthDate(nowDate)
                .position("")
                .createdAt(now)
                .active(true)
                .build();

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getFirstName()).isEqualTo("John");
        assertThat(response.getLastName()).isEqualTo("Doe");
        assertThat(response.getCreatedAt()).isEqualTo(now);
        assertThat(response.getActive()).isTrue();

        EmployeeResponse responseNoArgs = new EmployeeResponse();
    }

    @Test
    void testLombokConstructorsAndBuilders() {
        // Prueba constructor vacío y setters de Entidad
        Employee empEmpty = new Employee();
        empEmpty.setId(2L);
        assertThat(empEmpty.getId()).isEqualTo(2L);

        // Prueba constructor con todos los argumentos de Entidad
        Employee empAll = new Employee(1L, "A", "B", "C", "D", 20, Gender.MALE, LocalDate.now(), "Dev", true, LocalDateTime.now());
        assertThat(empAll.getId()).isEqualTo(1L);

        // Prueba constructor con todos los argumentos de Request
        EmployeeRequest reqAll = new EmployeeRequest("A", "B", "C", "D", 20, Gender.MALE, LocalDate.now(), "Dev", true);
        assertThat(reqAll.getFirstName()).isEqualTo("A");
    }

    @Test
    void testEmployeeGettersAndSetters() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setMiddleName("Michael");
        employee.setLastName("Doe");
        employee.setMaternalLastName("Smith");
        employee.setAge(30);
        employee.setGender(Gender.MALE);
        employee.setBirthDate(LocalDate.of(1994, 5, 12));
        employee.setPosition("Senior Developer");
        employee.setActive(true);
        employee.setCreatedAt(LocalDateTime.now());

        assertThat(employee.getId()).isEqualTo(1L);
        assertThat(employee.getFirstName()).isEqualTo("John");
        assertThat(employee.getMiddleName()).isEqualTo("Michael");
        assertThat(employee.getLastName()).isEqualTo("Doe");
        assertThat(employee.getMaternalLastName()).isEqualTo("Smith");
        assertThat(employee.getAge()).isEqualTo(30);
        assertThat(employee.getGender()).isEqualTo(Gender.MALE);
        assertThat(employee.getBirthDate()).isEqualTo(LocalDate.of(1994, 5, 12));
        assertThat(employee.getPosition()).isEqualTo("Senior Developer");
        assertThat(employee.getActive()).isTrue();
        assertThat(employee.getCreatedAt()).isNotNull();
    }

    @Test
    void testLombokGeneratedMethods() {
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .maternalLastName("Smith")
                .build();

        assertThat(employee.toString()).isNotNull();
        assertThat(employee.hashCode()).isNotZero();
        employee.getFirstName();
        employee.getId();
        employee.getActive();
        employee.getMaternalLastName();
        employee.getAge();
        employee.getBirthDate();
        employee.getCreatedAt();
        employee.getGender();
        employee.getLastName();
        employee.getMiddleName();
        employee.getPosition();
        employee.toString();
    }
}