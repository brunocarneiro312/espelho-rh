package br.com.imasoft.springsectemplate.service.it;

import br.com.imasoft.springsectemplate.controller.UserController;
import br.com.imasoft.springsectemplate.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * -----------------------------------
 * Teste de integração do domínio User
 * -----------------------------------
 * @author brunocarneiro
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserIT {

    @Autowired
    private UserController userController;

    @BeforeEach
    public void setUp() {
        System.out.println(userController);
    }

    @Test
    @DisplayName("Given user, when save, then save and return user")
    @Order(1)
    public void givenUser_whenSave_thenSaveAndReturnUser() {

        // given
        User user = new User.Builder()
                .key(UUID.randomUUID().toString())
                .name("Bruno Carneiro")
                .email("bruno.carneiro@imasoft.com.br")
                .password("123456")
                .birthdate(LocalDate.of(1987, 6, 29))
                .build();

        // when
        ResponseEntity<User> re = this.userController.save(user);

        // then
        assertTrue(re.getStatusCode().is2xxSuccessful());

    }

    @Test
    @DisplayName("Given id, when findById, then return user")
    @Order(2)
    public void givenId_whenFindById_thenReturnUser() {

        // given
        Integer id = 1;

        // when
        User user = this.userController.findById(id).getBody();

        // then
        assertNotNull(user);
    }

    @Test
    @DisplayName("Given user, when update, then return user updated")
    @Order(3)
    public void givenUser_whenUpdate_thenReturnUserUpdated() {

        // given
        User user = this.userController.findById(1).getBody();
        user.setName("Bruno Ferreira");
        user.setEmail("bruno.ferreira@imasoft.com.br");

        // when
        User updatedUser = this.userController.update(user).getBody();

        // then
        assertAll("Testando alteração de usuário",
                () -> assertNotNull(updatedUser, "O usuário não deveria estar nulo."),
                () -> assertEquals(updatedUser.getName(), "Bruno Ferreira", "O nome deveria ser o mesmo."),
                () -> assertEquals(updatedUser.getEmail(), "bruno.ferreira@imasoft.com.br", "O email deveria ser o mesmo."));

    }

    @Test
    @DisplayName("Given id, when delete, then return user removed")
    @Order(4)
    public void givenId_whenDelete_thenReturnUserRemoved() {

        // given
        Integer id = 1;

        // when
        User deletedUser = this.userController.delete(id).getBody();

        // then
        assertNotNull(deletedUser);
    }
}
