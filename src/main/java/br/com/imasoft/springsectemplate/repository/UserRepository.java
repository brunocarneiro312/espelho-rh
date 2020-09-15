package br.com.imasoft.springsectemplate.repository;

import br.com.imasoft.springsectemplate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author brunocarneiro
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);

    User findUserByEmail(String email);
}
