package com.ingeus.controller.user;

import static com.ingeus.util.ApiConstants.BASE_API;
import static com.ingeus.util.ApiConstants.USERS;
import static com.ingeus.util.ApiConstants.V1;

import com.ingeus.controller.user.request.UserRequest;
import com.ingeus.controller.user.response.AddUserResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BASE_API + V1 + USERS)
public class UserController {

    @PostMapping
    public AddUserResponse addUser(@RequestBody @Valid @NotNull UserRequest userRequest) {
        return new AddUserResponse(1L);
    }
}
