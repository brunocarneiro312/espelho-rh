package br.com.imasoft.espelhorh.repository;

import br.com.imasoft.espelhorh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author brunocarneiro
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);

    User findUserByEmail(String email);
}
