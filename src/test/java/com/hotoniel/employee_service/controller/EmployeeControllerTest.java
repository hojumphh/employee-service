package com.hotoniel.employee_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotoniel.employee_service.domain.enums.Gender;
import com.hotoniel.employee_service.dto.request.EmployeeRequest;
import com.hotoniel.employee_service.dto.response.EmployeeResponse;
import com.hotoniel.employee_service.exception.EmployeeNotFoundException;
import com.hotoniel.employee_service.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void getAllEmployees_ShouldReturn200() throws Exception {
        EmployeeResponse response = EmployeeResponse.builder().id(1L).firstName("John").build();
        when(employeeService.getAllEmployees()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void getEmployeeById_ShouldReturn200_WhenExists() throws Exception {
        EmployeeResponse response = EmployeeResponse.builder().id(1L).firstName("John").build();
        when(employeeService.getEmployeeById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void getEmployeeById_ShouldReturn404_WhenNotFound() throws Exception {
        when(employeeService.getEmployeeById(1L)).thenThrow(new EmployeeNotFoundException("Not found"));

        mockMvc.perform(get("/api/v1/employees/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createEmployees_ShouldReturn201() throws Exception {
        EmployeeRequest request = EmployeeRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .maternalLastName("Smith")
                .age(30)
                .gender(Gender.MALE)
                .birthDate(LocalDate.of(1994, 5, 12))
                .position("Developer")
                .build();

        EmployeeResponse response = EmployeeResponse.builder().id(1L).firstName("John").build();
        when(employeeService.saveEmployees(any())).thenReturn(List.of(response));

        mockMvc.perform(post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(List.of(request))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void deleteEmployee_ShouldReturn204() throws Exception {
        doNothing().when(employeeService).deleteEmployee(1L);

        mockMvc.perform(delete("/api/v1/employees/1"))
                .andExpect(status().isNoContent());
    }
}