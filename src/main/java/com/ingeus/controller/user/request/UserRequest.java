package com.ingeus.controller.user.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static com.ingeus.util.ApiConstants.DATE_FORMAT;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private Long id;

    @NotEmpty
    @NotNull
    @Size(max = 200)
    private String firstName;

    @NotEmpty
    @NotNull
    @Size(max = 200)
    private String lastName;

    @JsonFormat(pattern = DATE_FORMAT)
    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    @Size(min = 6, max = 50)
    private String password;
}
