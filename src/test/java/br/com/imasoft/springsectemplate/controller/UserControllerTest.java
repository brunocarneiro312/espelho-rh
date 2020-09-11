package br.com.imasoft.springsectemplate.controller;

import br.com.imasoft.springsectemplate.model.User;
import br.com.imasoft.springsectemplate.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * --------------------
 * Teste da API de User
 * --------------------
 * @author brunocarneiro
 */
@SpringBootTest
class UserControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("When save, then return ok (200)")
    public void givenUser_whenSave_thenReturn200() throws Exception {

        // given
        User user = new User.Builder()
                .key(UUID.randomUUID().toString())
                .name("Bruno Carneiro")
                .email("bruno.carneiro@imasoft.com.br")
                .password("123456")
                .birthdate(LocalDate.of(1987, 7, 29))
                .build();

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String jsonRequest = mapper.writeValueAsString(user);

        // when / then
        mockMvc.perform(
                post("/api/v1/service/user")
                    .content(jsonRequest)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .with(httpBasic("common", "123456")))
                .andExpect(status().isOk());
    }
}