package com.ingeus.controller.user.response;

import com.ingeus.repository.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

import static com.ingeus.util.ApiConstants.DATE_FORMAT;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    public static UserResponse fromUser(User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth()
                        .format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .build();
    }
}
