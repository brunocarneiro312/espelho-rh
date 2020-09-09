package br.com.imasoft.springsectemplate.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ----------------------------
 * Teste da classe de segurança
 * ----------------------------
 * @author brunocarneiro
 */
@SpringBootTest
class SecurityConfigTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    /**
     * --------------
     * Anonymous test
     * --------------
     */
    @Test
    @DisplayName("Given anonymous user, when access service, then return forbidden (403)")
    @WithAnonymousUser
    public void givenAnonymousUser_whenAccessService_thenReturn401() throws Exception {
        mockMvc.perform(get("/api/v1/service/user"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Given Anonymous user, when access admin, then return forbidden (403)")
    @WithAnonymousUser
    public void givenAnonymousUser_whenAccessAdmin_thenReturn401() throws Exception {
        mockMvc.perform(get("/api/v1/admin"))
                .andExpect(status().isUnauthorized());
    }

    /**
     * ---------
     * User test
     * ---------
     */
    @Test
    @DisplayName("Given authenticated user, when access service, then return ok (200)")
    public void givenAuthenticatedUser_whenAccessService_thenReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/service/user").with(httpBasic("common", "123456")))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Given authenticated user, wher access admin, then return forbidden (403)")
    public void givenAuthenticatedUser_whenAccessAdmin_thenReturn403() throws Exception {
        mockMvc.perform(get("/api/v1/admin").with(httpBasic("common", "123456")))
                .andExpect(status().isForbidden());
    }

    /**
     * ----------
     * Admin test
     * ----------
     */
    @Test
    @DisplayName("Given authenticated admin, when access service, then return ok (200)")
    public void givenAuthenticatedAdmin_whenAccessService_thenReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/service/user")
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Given authenticated admin, when access admin, then return ok (200)")
    public void givenAuthenticatedAdmin_whenAccessAdmin_thenReturn200() throws Exception {
        mockMvc.perform(get("/api/v1/admin")
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isOk());
    }
}