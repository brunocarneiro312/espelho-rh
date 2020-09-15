package br.com.imasoft.springsectemplate.controller;

import br.com.imasoft.springsectemplate.config.MyUserDetails;
import br.com.imasoft.springsectemplate.model.Role;
import br.com.imasoft.springsectemplate.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PublicControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void beforeAll() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    }

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("Given user, when sign up, then return user")
    public void signUp() throws Exception {

        // given
        User user = new User.Builder()
                .key(UUID.randomUUID().toString())
                .name("Test User")
                .email("test.user@imasoft.com.br")
                .password("123456")
                .birthdate(LocalDate.of(1987, 7, 29))
                .build();

        String jsonRequest = objectMapper.writeValueAsString(user);

        // when / then
        mockMvc.perform(
                post("/api/v1/public")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .with(user(new MyUserDetails(
                                new User.Builder()
                                        .name("Test User")
                                        .email("test.user@imasoft.com.br")
                                        .birthdate(LocalDate.now())
                                        .password("123456")
                                        .roles(Collections.singletonList(new Role("ADMIN")))
                                        .build()
                        )))).andExpect(status().isOk());
    }

}