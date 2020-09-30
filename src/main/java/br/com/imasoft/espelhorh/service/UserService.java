package br.com.imasoft.espelhorh.service;

import br.com.imasoft.espelhorh.model.User;

/**
 * @author brunocarneiro
 */
public interface UserService extends CrudService<User> {

    User findByName(String username) throws Exception;

    User findUserByEmail(String email) throws Exception;

}
