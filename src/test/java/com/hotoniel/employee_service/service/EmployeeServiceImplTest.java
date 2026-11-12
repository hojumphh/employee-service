package com.hotoniel.employee_service.service;

import com.hotoniel.employee_service.domain.entity.Employee;
import com.hotoniel.employee_service.domain.enums.Gender;
import com.hotoniel.employee_service.dto.request.EmployeeRequest;
import com.hotoniel.employee_service.dto.response.EmployeeResponse;
import com.hotoniel.employee_service.exception.EmployeeNotFoundException;
import com.hotoniel.employee_service.mapper.EmployeeMapper;
import com.hotoniel.employee_service.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private EmployeeRequest employeeRequest;
    private EmployeeResponse employeeResponse;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .maternalLastName("Smith")
                .age(30)
                .gender(Gender.MALE)
                .birthDate(LocalDate.of(1994, 5, 12))
                .position("Developer")
                .active(true)
                .build();

        employeeRequest = EmployeeRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .maternalLastName("Smith")
                .age(30)
                .gender(Gender.MALE)
                .birthDate(LocalDate.of(1994, 5, 12))
                .position("Developer")
                .active(true)
                .build();

        employeeResponse = EmployeeResponse.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .maternalLastName("Smith")
                .age(30)
                .gender(Gender.MALE)
                .birthDate(LocalDate.of(1994, 5, 12))
                .position("Developer")
                .active(true)
                .build();
    }

    @Test
    void getAllEmployees_ShouldReturnList() {
        when(employeeRepository.findByActiveTrue()).thenReturn(List.of(employee));
        when(employeeMapper.toResponse(employee)).thenReturn(employeeResponse);

        List<EmployeeResponse> result = employeeService.getAllEmployees();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        verify(employeeRepository, times(1)).findByActiveTrue();
    }

    @Test
    void getEmployeeById_ShouldReturnEmployee_WhenExists() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeMapper.toResponse(employee)).thenReturn(employeeResponse);

        EmployeeResponse result = employeeService.getEmployeeById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void getEmployeeById_ShouldThrowException_WhenNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.getEmployeeById(1L))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessageContaining("Employee not found with id: 1");
    }

    @Test
    void saveEmployees_ShouldReturnSavedList() {
        when(employeeMapper.toEntity(any(EmployeeRequest.class))).thenReturn(employee);
        when(employeeRepository.saveAll(anyList())).thenReturn(List.of(employee));
        when(employeeMapper.toResponse(employee)).thenReturn(employeeResponse);

        List<EmployeeResponse> result = employeeService.saveEmployees(List.of(employeeRequest));

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        verify(employeeRepository, times(1)).saveAll(anyList());
    }

    @Test
    void updateEmployee_ShouldUpdateAndReturnResponse_WhenExists() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        doNothing().when(employeeMapper).updateEntity(any(Employee.class), any(EmployeeRequest.class));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(employeeMapper.toResponse(employee)).thenReturn(employeeResponse);

        EmployeeResponse result = employeeService.updateEmployee(1L, employeeRequest);

        assertThat(result).isNotNull();
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void updateEmployee_ShouldThrowException_WhenNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.updateEmployee(1L, employeeRequest))
                .isInstanceOf(EmployeeNotFoundException.class);
    }

    @Test
    void deleteEmployee_ShouldDelete_WhenExists() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        employeeService.deleteEmployee(1L);

        // Verificamos que se buscó, se modificó a inactivo y se guardó (en lugar de deleteById)
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(employee);
        assertThat(employee.getActive()).isFalse();
    }


    @Test
    void searchEmployeesByName_ShouldReturnList() {
        when(employeeRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase("John", "John"))
                .thenReturn(List.of(employee));
        when(employeeMapper.toResponse(employee)).thenReturn(employeeResponse);

        List<EmployeeResponse> result = employeeService.searchEmployeesByName("John");

        assertThat(result).hasSize(1);
        verify(employeeRepository, times(1)).findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(anyString(), anyString());
    }
}