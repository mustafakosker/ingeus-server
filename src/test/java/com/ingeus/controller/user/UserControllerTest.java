package com.ingeus.controller.user;

import static com.ingeus.util.ApiConstants.BASE_API;
import static com.ingeus.util.ApiConstants.USERS;
import static com.ingeus.util.ApiConstants.V1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.ingeus.UserApiApplication;
import com.ingeus.controller.user.request.UserRequest;
import com.ingeus.controller.user.response.AddUserResponse;
import java.time.LocalDate;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApiApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    private final String USERS_ENDPOINT = BASE_API + V1 + USERS;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        restTemplate.getRestTemplate().setInterceptors(
                Collections.singletonList((request, body, execution) -> {
                    request.getHeaders()
                            .add(HttpHeaders.CONTENT_TYPE, "application/json");
                    return execution.execute(request, body);
                }));
    }

    @Test
    public void shouldAddUserSuccessfullyWhenRequestIsValid() {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.now())
                .password("Test124123").build();

        final AddUserResponse addUserResponse = this.restTemplate
                .postForEntity(USERS_ENDPOINT, userRequest, AddUserResponse.class)
                .getBody();

        assertThat(addUserResponse).isEqualTo(new AddUserResponse(1L));
    }

    @Test
    public void shouldReturnBadRequestWhenRequestIsNull() throws Exception {
        final ResponseEntity<AddUserResponse> responseEntity = this.restTemplate
                .postForEntity(USERS_ENDPOINT, null, AddUserResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenFirstNameIsNull() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName(null)
                .lastName("Doe")
                .dateOfBirth(LocalDate.now())
                .password("Test124123").build();

        final ResponseEntity<AddUserResponse> responseEntity = this.restTemplate
                .postForEntity(USERS_ENDPOINT, userRequest, AddUserResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenFirstNameIsLongerThanMaxLength() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName(String.join("", Collections.nCopies(201, "a")))
                .lastName("Doe")
                .dateOfBirth(LocalDate.now())
                .password("Test124123").build();

        final ResponseEntity<AddUserResponse> responseEntity = this.restTemplate
                .postForEntity(USERS_ENDPOINT, userRequest, AddUserResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenFirstNameIsEmpty() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("")
                .lastName("Doe")
                .dateOfBirth(LocalDate.now())
                .password("Test124123").build();

        final ResponseEntity<AddUserResponse> responseEntity = this.restTemplate
                .postForEntity(USERS_ENDPOINT, userRequest, AddUserResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenLastNameIsNull() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("John")
                .lastName(null)
                .dateOfBirth(LocalDate.now())
                .password("Test124123").build();

        final ResponseEntity<AddUserResponse> responseEntity = this.restTemplate
                .postForEntity(USERS_ENDPOINT, userRequest, AddUserResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenLastNameIsLongerThanMaxLength() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("John")
                .lastName(String.join("", Collections.nCopies(201, "a")))
                .dateOfBirth(LocalDate.now())
                .password("Test124123").build();

        final ResponseEntity<AddUserResponse> responseEntity = this.restTemplate
                .postForEntity(USERS_ENDPOINT, userRequest, AddUserResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenLastNameIsEmpty() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("John")
                .lastName("")
                .dateOfBirth(LocalDate.now())
                .password("Test124123").build();

        final ResponseEntity<AddUserResponse> responseEntity = this.restTemplate
                .postForEntity(USERS_ENDPOINT, userRequest, AddUserResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenDateOfBirthIsNull() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(null)
                .password("Test124123").build();

        final ResponseEntity<AddUserResponse> responseEntity = this.restTemplate
                .postForEntity(USERS_ENDPOINT, userRequest, AddUserResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenPasswordIsNull() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.now())
                .password(null).build();

        final ResponseEntity<AddUserResponse> responseEntity = this.restTemplate
                .postForEntity(USERS_ENDPOINT, userRequest, AddUserResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenPasswordLengthSmallerThanMinSize() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.now())
                .password("1234").build();

        final ResponseEntity<AddUserResponse> responseEntity = this.restTemplate
                .postForEntity(USERS_ENDPOINT, userRequest, AddUserResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenPasswordIsLongerThanMaxLength() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.now())
                .password(String.join("", Collections.nCopies(51, "a"))).build();

        final ResponseEntity<AddUserResponse> responseEntity = this.restTemplate
                .postForEntity(USERS_ENDPOINT, userRequest, AddUserResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

}
