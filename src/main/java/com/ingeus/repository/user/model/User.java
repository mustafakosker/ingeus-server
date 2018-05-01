package com.ingeus.repository.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    @Size(max = 200)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(max = 200)
    private String lastName;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    @NotEmpty
    private String password;
}
