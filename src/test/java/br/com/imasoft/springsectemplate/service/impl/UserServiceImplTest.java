package br.com.imasoft.springsectemplate.service.impl;

import br.com.imasoft.springsectemplate.model.User;
import br.com.imasoft.springsectemplate.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

/**
 * ---------------------------------------------------
 * Teste unitário da classe de serviço UserServiceImpl
 * ---------------------------------------------------
 * @author brunocarneiro
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Given user id, when findById, then return user")
    void findById() {

        // given
        Integer userId = 1;
        given(userRepository.findById(Mockito.anyInt())).willReturn(Optional.of(new User()));

        // when
        User user = this.userService.findById(userId);

        // then
        assertNotNull(user);
        Mockito.verify(userRepository).findById(Mockito.anyInt());
    }

    @Test
    @DisplayName("When findAll, then return all users")
    void findAll() {

        // given
        given(userRepository.findAll()).willReturn(
                new ArrayList<>(
                        Arrays.asList(new User(), new User())));

        // when
        List<User> users = this.userService.findAll();

        // then
        assertNotNull(users);
        assertEquals(2, users.size());
        then(userRepository).should(times(1)).findAll();
    }

    @Test
    @DisplayName("Given user, when save, then persist user")
    void save() throws Exception {

        // given
        User user = this.getUser();

        given(userRepository.save(user)).willReturn(user);

        // when
        User savedUser = this.userService.save(user);

        // then
        assertAll("Validando usuário",
                () -> assertNotNull(savedUser, "O usuário não deveria estar nulo."),
                () -> assertEquals(36, user.getKey().length(), "A chave do usuário não parece ser uma UUID."),
                () -> assertEquals("Bruno Carneiro", savedUser.getName(), "O nome não corresponde."),
                () -> assertEquals("bruno.carneiro@imasoft.com.br", savedUser.getEmail(), "O email não corresponde.")

        );

        then(userRepository).should().save(Mockito.any());
    }

    @Test
    @DisplayName("Given user, when update, then update user")
    void update() {

        // given
        User user = this.getUser();
        given(userService.update(user)).willReturn(user);

        // when
        user.setName("Bruno Ferreira");
        User updatedUser = this.userService.update(user);

        // then
        assertAll("Validando usuário",
                () -> assertNotNull(updatedUser, "O usuário não deveria estar nulo"),
                () -> assertEquals(36, updatedUser.getKey().length(), "A chave do usuário não parece ser uma UUID."),
                () -> assertEquals("Bruno Ferreira", updatedUser.getName(), "O nome não corresponde."));

        then(userRepository).should().saveAndFlush(Mockito.any());
    }

    @Test
    @DisplayName("Given user id, when delete, then delete user")
    void deleteById() {

        // given
        User user = this.getUser();
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));

        // when
        User deletedUser = userService.deleteById(user.getId());

        // then
        then(userRepository).should().deleteById(user.getId());
        assertAll("Teste de exclusão",
                () -> assertNotNull(deletedUser, "O usuário não deveria estar nulo.")
        );

    }

    private User getUser() {
        return new User.Builder()
                .key(UUID.randomUUID().toString())
                .name("Bruno Carneiro")
                .email("bruno.carneiro@imasoft.com.br")
                .birthdate(LocalDate.of(1987, 6, 29))
                .password("123456")
                .build();
    }
}