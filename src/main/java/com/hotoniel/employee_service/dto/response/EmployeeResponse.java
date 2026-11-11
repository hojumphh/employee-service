package com.hotoniel.employee_service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotoniel.employee_service.domain.enums.Gender;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String maternalLastName;
    private Integer age;
    private Gender gender;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    private String position;
    private Boolean active;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
