package br.com.imasoft.springsectemplate.repository;

import br.com.imasoft.springsectemplate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
