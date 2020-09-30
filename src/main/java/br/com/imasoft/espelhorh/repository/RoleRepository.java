package br.com.imasoft.espelhorh.repository;

import br.com.imasoft.espelhorh.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author brunocarneiro
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(String roleName) throws Exception;

    void deleteByRoleName(String roleName) throws Exception;

}
