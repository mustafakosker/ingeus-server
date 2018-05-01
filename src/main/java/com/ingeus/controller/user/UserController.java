package com.ingeus.controller.user;

import com.ingeus.controller.user.request.UserRequest;
import com.ingeus.controller.user.response.UserResponse;
import com.ingeus.exception.NotFoundException;
import com.ingeus.repository.user.UserRepository;
import com.ingeus.repository.user.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.ingeus.util.ApiConstants.*;

@RestController
@RequestMapping(BASE_API + V1 + USERS)
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public UserResponse saveUser(@RequestBody @Valid @NotNull UserRequest userRequest) {
        final User savedUser = userRepository.save(createUser(userRequest));

        return UserResponse.fromUser(savedUser);
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody @Valid @NotNull UserRequest userRequest,
                           @PathVariable("id") Long id) {
        userRequest.setId(id);

        userRepository.save(createUser(userRequest));
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable("id") Long id) {
        return userRepository.findById(id)
                .map(UserResponse::fromUser)
                .orElseThrow(() -> new NotFoundException("No user found with given id"));
    }

    private User createUser(UserRequest userRequest){
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .dateOfBirth(userRequest.getDateOfBirth())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();
    }
}
