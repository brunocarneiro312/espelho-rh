package br.com.imasoft.espelhorh.service;


import br.com.imasoft.espelhorh.model.Role;

/**
 * @author brunocarneiro
 */
public interface RoleService extends CrudService<Role> {

    Role findByRoleName(String roleName) throws Exception;

    Role deleteByRoleName(String roleName) throws Exception;

}
