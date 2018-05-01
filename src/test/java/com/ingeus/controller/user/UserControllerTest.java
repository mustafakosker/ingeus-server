package com.ingeus.controller.user;

import com.ingeus.UserApiApplication;
import com.ingeus.controller.user.request.UserRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;

import static com.ingeus.util.ApiConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

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
    public void shouldSaveUserSuccessfullyWhenRequestIsValid() {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.now())
                .password("Test124123").build();

        ResponseEntity responseEntity = this.restTemplate
                .exchange(USERS_ENDPOINT + "/1", HttpMethod.PUT, new HttpEntity<>(userRequest, null), Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
    }

    @Test
    public void shouldReturnBadRequestWhenRequestIsNull() throws Exception {
        final ResponseEntity responseEntity = this.restTemplate
                .exchange(USERS_ENDPOINT + "/1", HttpMethod.PUT, new HttpEntity<>(null, null), Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenFirstNameIsNull() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName(null)
                .lastName("Doe")
                .dateOfBirth(LocalDate.now())
                .password("Test124123").build();

        final ResponseEntity responseEntity = this.restTemplate
                .exchange(USERS_ENDPOINT + "/1", HttpMethod.PUT, new HttpEntity<>(userRequest, null), Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenFirstNameIsLongerThanMaxLength() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName(String.join("", Collections.nCopies(201, "a")))
                .lastName("Doe")
                .dateOfBirth(LocalDate.now())
                .password("Test124123").build();

        final ResponseEntity responseEntity = this.restTemplate
                .exchange(USERS_ENDPOINT + "/1", HttpMethod.PUT, new HttpEntity<>(userRequest, null), Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenFirstNameIsEmpty() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("")
                .lastName("Doe")
                .dateOfBirth(LocalDate.now())
                .password("Test124123").build();

        final ResponseEntity responseEntity = this.restTemplate
                .exchange(USERS_ENDPOINT + "/1", HttpMethod.PUT, new HttpEntity<>(userRequest, null), Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenLastNameIsNull() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("John")
                .lastName(null)
                .dateOfBirth(LocalDate.now())
                .password("Test124123").build();

        final ResponseEntity responseEntity = this.restTemplate
                .exchange(USERS_ENDPOINT + "/1", HttpMethod.PUT, new HttpEntity<>(userRequest, null), Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenLastNameIsLongerThanMaxLength() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("John")
                .lastName(String.join("", Collections.nCopies(201, "a")))
                .dateOfBirth(LocalDate.now())
                .password("Test124123").build();

        final ResponseEntity responseEntity = this.restTemplate
                .exchange(USERS_ENDPOINT + "/1", HttpMethod.PUT, new HttpEntity<>(userRequest, null), Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenLastNameIsEmpty() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("John")
                .lastName("")
                .dateOfBirth(LocalDate.now())
                .password("Test124123").build();

        final ResponseEntity responseEntity = this.restTemplate
                .exchange(USERS_ENDPOINT + "/1", HttpMethod.PUT, new HttpEntity<>(userRequest, null), Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenDateOfBirthIsNull() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(null)
                .password("Test124123").build();

        final ResponseEntity responseEntity = this.restTemplate
                .exchange(USERS_ENDPOINT + "/1", HttpMethod.PUT, new HttpEntity<>(userRequest, null), Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenPasswordIsNull() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.now())
                .password(null).build();

        final ResponseEntity responseEntity = this.restTemplate
                .exchange(USERS_ENDPOINT + "/1", HttpMethod.PUT, new HttpEntity<>(userRequest, null), Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenPasswordLengthSmallerThanMinSize() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.now())
                .password("1234").build();

        final ResponseEntity responseEntity = this.restTemplate
                .exchange(USERS_ENDPOINT + "/1", HttpMethod.PUT, new HttpEntity<>(userRequest, null), Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestWhenPasswordIsLongerThanMaxLength() throws Exception {
        final UserRequest userRequest = UserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.now())
                .password(String.join("", Collections.nCopies(51, "a"))).build();

        final ResponseEntity responseEntity = this.restTemplate
                .exchange(USERS_ENDPOINT + "/1", HttpMethod.PUT, new HttpEntity<>(userRequest, null), Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

}
