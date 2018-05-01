package com.ingeus.controller.user;

import static com.ingeus.util.ApiConstants.BASE_API;
import static com.ingeus.util.ApiConstants.USERS;
import static com.ingeus.util.ApiConstants.V1;
import static org.assertj.core.api.Assertions.assertThat;

import com.ingeus.UserApiApplication;
import com.ingeus.controller.user.request.UserRequest;
import com.ingeus.controller.user.response.AddUserResponse;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApiApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAddUserSuccess() {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.now())
                .password("Test124123").build();

        final AddUserResponse addUserResponse = this.restTemplate
                .postForEntity(BASE_API + V1 + USERS, userRequest, AddUserResponse.class)
                .getBody();
        assertThat(addUserResponse).isEqualTo(new AddUserResponse(1L));
    }
}
