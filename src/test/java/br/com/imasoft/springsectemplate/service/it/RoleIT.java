package br.com.imasoft.springsectemplate.service.it;

import br.com.imasoft.springsectemplate.model.Role;
import br.com.imasoft.springsectemplate.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Autowired
    private RoleServiceImpl roleService;

    @Test
    @DisplayName("Given role, when save, then return role")
    @Order(1)
    public void givenRole_whenSave_thenReturnRole() throws Exception {

        // given
        Role roleCommon = getRoleCommon();
        Role roleAdmin = getRoleAdmin();

        // when
        Role roleCommonSaved = roleService.save(roleCommon);
        Role roleAdminSaved = roleService.save(roleAdmin);

        // then
        assertAll("Test Save",
                () -> assertNotNull(roleCommonSaved, "Não deveria estar nulo"),
                () -> assertNotNull(roleAdminSaved, "Não deveria estar nulo"),
                () -> assertEquals("common", roleCommonSaved.getRoleName()),
                () -> assertEquals("admin", roleAdminSaved.getRoleName()));
    }

    @Test
    @DisplayName("Given role id, when findById, then return role")
    @Order(2)
    public void givenRoleId_whenFindById_thenReturnRole() throws Exception {

        // given
        Integer id = 1;

        // when
        Role role = roleService.findById(id);

        // then
        assertAll("Test FindById",
                () -> assertNotNull(role),
                () -> assertEquals("common", role.getRoleName()));
    }

    @Test
    @DisplayName("When findAll, then return roles")
    @Order(3)
    public void whenFindAll_thenReturnRoles() throws Exception {

        // when
        List<Role> roles = roleService.findAll();

        // then
        assertAll("Test FindAll",
                () -> assertNotNull(roles),
                () -> assertEquals(2, roles.size()));
    }

    @Test
    @DisplayName("Given role, when update, then return role")
    @Order(4)
    public void givenRole_whenUpdate_thenReturnRole() throws Exception {

        // given
        Role roleAdmin = getRoleAdmin();
        roleAdmin.setRoleName("adminUpdated");

        // when
        Role roleUpdated = roleService.update(roleAdmin);

        // then
        assertAll("Test Update",
                () -> assertNotNull(roleUpdated),
                () -> assertEquals("adminUpdated", roleUpdated.getRoleName()));
    }



    @Test
    @DisplayName("Given role id, when delete, then return role")
    @Order(5)
    public void givenRoleId_whenDelete_thenReturnRole() throws Exception {

        // given
        Integer id = 1;

        // when
        Role roleDeleted = roleService.deleteById(id);

        // then
        assertAll("Test Delete",
                () -> assertNotNull(roleDeleted, "Não deveria estar nulo."),
                () -> assertEquals(1, roleDeleted.getId(), "O id deveria ser o mesmo."));
    }

    private Role getRoleAdmin() {
        return new Role.Builder().roleName("admin").build();
    }

    private Role getRoleCommon() {
        return new Role.Builder().roleName("common").build();
    }

}
