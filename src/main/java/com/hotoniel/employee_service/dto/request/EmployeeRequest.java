package com.hotoniel.employee_service.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotoniel.employee_service.domain.enums.Gender;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequest {

    @NotBlank(message = "First name is mandatory")
    private String firstName;
    private String middleName;
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @NotBlank(message = "Mother's last name is mandatory")
    private String maternalLastName;
    @NotNull(message = "Age is mandatory")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than 100")
    private Integer age;
    @NotNull(message = "Gender is mandatory")
    private Gender gender;
    @NotNull(message = "Birth date is mandatory")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    @NotBlank(message = "Position is mandatory")
    private String position;
    private Boolean active;
}
