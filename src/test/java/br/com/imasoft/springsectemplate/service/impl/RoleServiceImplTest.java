package br.com.imasoft.springsectemplate.service.impl;

import br.com.imasoft.springsectemplate.model.Role;
import br.com.imasoft.springsectemplate.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * ---------------------------------------------------
 * Teste unitário da classe de serviço RoleServiceImpl
 * ---------------------------------------------------
 *
 * @author brunocarneiro
 */
@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {

    @InjectMocks
    RoleServiceImpl roleService;

    @Mock
    RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {

    }

    @Test
    @DisplayName("Given role, when save, then return role")
    public void save() throws Exception {

        // given
        Role roleCommon = new Role.Builder().id(1).roleName("COMMON").build();
        Role roleAdmin = new Role.Builder().id(2).roleName("ADMIN").build();

        given(roleRepository.save(roleCommon)).willReturn(roleCommon);
        given(roleRepository.save(roleAdmin)).willReturn(roleAdmin);

        // when
        Role roleCommonSaved = this.roleService.save(roleCommon);
        Role roleAdminSaved = this.roleService.save(roleAdmin);

        // then
        assertAll("Validando retorno do teste de persistência",
                () -> assertAll("Validando role common",
                        () -> assertNotNull(roleCommonSaved, "A role não deveria estar nula."),
                        () -> assertEquals(1, roleCommon.getId(), "O id deveria ser o mesmo."),
                        () -> assertEquals("COMMON", roleCommonSaved.getRoleName(), "O nome deveria ser o mesmo.")),
                () -> assertAll("Validando role admin",
                        () -> assertNotNull(roleAdminSaved, "A role não deveria estar nula."),
                        () -> assertEquals(2, roleAdmin.getId(), "O id deveria ser o mesmo."),
                        () -> assertEquals("ADMIN", roleAdminSaved.getRoleName(), "O nome deveria ser o mesmo.")));
    }

    @Test
    @DisplayName("Given role, when update, then return role")
    public void update() throws Exception {

        // given
        Role role = new Role.Builder().id(1).roleName("admin").build();
        given(roleRepository.saveAndFlush(role)).willReturn(role);

        // when
        Role roleUpdated = roleService.update(role);

        // then
        then(roleRepository).should().saveAndFlush(role);
        assertAll("Update role",
                () -> assertNotNull(roleUpdated, "A role não deveria estar nula."),
                () -> assertEquals(1, roleUpdated.getId(), "O id deveria ser o mesmo."),
                () -> assertEquals("admin", role.getRoleName(), "O nome deveria ser o mesmo."));
    }

    @Test
    @DisplayName("Given role id, when delete, then return role")
    public void delete() throws Exception {

        // given
        Integer id = 1;
        given(roleRepository.findById(id)).willReturn(
                Optional.of(
                        new Role.Builder()
                                .id(1)
                                .roleName("admin")
                                .build()));

        // when
        Role roleDeleted = roleService.deleteById(id);

        // then
        then(roleRepository).should().delete(any(Role.class));
        assertAll("Delete role",
                () -> assertNotNull(roleDeleted, "A role não deveria estar nula."),
                () -> assertEquals(1, roleDeleted.getId(), "O id deveria ser o mesmo."));
    }

    @Test
    @DisplayName("When list, then return list of roles")
    public void findAll() throws Exception {

        // given
        given(roleService.findAll()).willReturn(
                new ArrayList<>(Arrays.asList(
                        new Role.Builder().id(1).roleName("admin").build(),
                        new Role.Builder().id(2).roleName("common").build()
                )));

        // when
        List<Role> roles = roleService.findAll();

        // then
        then(roleRepository).should().findAll();
        assertAll("List roles",
                () -> assertNotNull(roles, "A lista de roles não deveria estar nula."),
                () -> assertEquals(2, roles.size(), "O tamanho da lista de roles deveria ser igual a 2."));

    }

    @Test
    @DisplayName("Given role id, when findById, then return role")
    public void findById() throws Exception {

        // given
        Integer id = 1;
        given(roleRepository.findById(anyInt()))
                .willReturn(Optional.of(
                        new Role.Builder().id(1).roleName("admin").build()));

        // when
        Role role = roleService.findById(id);

        // then
        assertAll("Find role",
                () -> assertNotNull(role, "A role não deveria estar nula."),
                () -> assertEquals(1, role.getId(), "O id deveria ser o mesmo."),
                () -> assertEquals("admin", role.getRoleName(), "O nome deveria ser o mesmo."));

    }
}
