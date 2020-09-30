package br.com.imasoft.espelhorh.service.it;

import br.com.imasoft.espelhorh.model.Role;
import br.com.imasoft.espelhorh.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ---------------------------
 * Teste de integração da Role
 * ---------------------------
 * @author brunocarneiro
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RoleIT {

    private List<Role> roles = new ArrayList<>();

    @Autowired
    private RoleServiceImpl roleService;

    @Test
    @DisplayName("Given role, when save, then return role")
    @Order(1)
    public void givenRole_whenSave_thenReturnRole() throws Exception {

        // given
        Role roleCommon = getRole();

        // when
        Role newRoleSaved = roleService.save(roleCommon);

        // then
        assertAll("Test Save",
                () -> assertNotNull(newRoleSaved, "Não deveria estar nulo"),
                () -> assertEquals("ROLE_TEST", newRoleSaved.getRoleName(), "A role deveria ser igual a ROLE_TEST"));
    }

    @Test
    @DisplayName("Given role id, when findById, then return role")
    @Order(2)
    public void givenRoleId_whenFindById_thenReturnRole() throws Exception {

        // given
        Integer id = 1;

        // when
        Role role = roleService.findByRoleName("ROLE_TEST");

        // then
        assertAll("Test FindById",
                () -> assertNotNull(role),
                () -> assertEquals("ROLE_TEST", role.getRoleName()));
    }

    @Test
    @DisplayName("When findAll, then return roles")
    @Order(3)
    public void whenFindAll_thenReturnRoles() throws Exception {

        // when
        this.roles = roleService.findAll();

        // then
        assertAll("Test FindAll",
                () -> assertNotNull(this.roles),
                () -> assertTrue(!this.roles.isEmpty()));
    }

    @Test
    @DisplayName("Given role, when update, then return role")
    @Order(4)
    public void givenRole_whenUpdate_thenReturnRole() throws Exception {

        // given
        Role roleAdmin = getRole();
        roleAdmin.setRoleName("ROLE_TEST2");

        // when
        Role roleUpdated = roleService.update(roleAdmin);

        // then
        assertAll("Test Update",
                () -> assertNotNull(roleUpdated),
                () -> assertEquals("ROLE_TEST2", roleUpdated.getRoleName()));
    }



    @Test
    @DisplayName("Given role name, when delete, then return role")
    @Order(5)
    public void givenRoleName_whenDelete_thenReturnRole() throws Exception {

        // given
        String roleName = this.getRole().getRoleName();

        // when
        Role roleDeleted = roleService.deleteByRoleName(roleName);

        // then
        assertAll("Test Delete",
                () -> assertNotNull(roleDeleted, "Não deveria estar nulo."));
    }

    private Role getRole() {
        return new Role.Builder().roleName("ROLE_TEST").build();
    }

}
