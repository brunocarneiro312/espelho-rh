package br.com.imasoft.springsectemplate.service.impl;

import br.com.imasoft.springsectemplate.model.User;
import br.com.imasoft.springsectemplate.repository.UserRepository;
import br.com.imasoft.springsectemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // Verifica se o usuário possui uma chave válida
    private final Predicate<User> hasKey = user -> user == null
            || user.getKey() == null
            || user.getKey().length() != 36;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Integer id) {
        return this.userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User save(User user) throws Exception {
        try {
            if (hasKey.test(user)) {
                user.setKey(UUID.randomUUID().toString());
            }
            return this.userRepository.save(user);
        }
        catch (Exception e) {
            throw new Exception("Houve um erro durante o cadastro de usuário.", e);
        }
    }

    @Override
    public User update(User user) {
        return this.userRepository.saveAndFlush(user);
    }

    @Override
    public User deleteById(Integer id) {
        User deletedUser = this.findById(id);
        if (deletedUser != null) {
            this.userRepository.deleteById(id);
            return deletedUser;
        }
        return null;
    }

}
