package com.ingeus.controller.user.request;

import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotEmpty
    @NotNull
    @Size(max = 200)
    private String firstName;

    @NotEmpty
    @NotNull
    @Size(max = 200)
    private String lastName;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    @Size(min = 6, max = 50)
    private String password;
}
