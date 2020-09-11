package br.com.imasoft.springsectemplate.service.impl;

import br.com.imasoft.springsectemplate.model.User;
import br.com.imasoft.springsectemplate.repository.UserRepository;
import br.com.imasoft.springsectemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private List<User> cachedUsers;

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
    public List<User> findAll() throws Exception {
        return this.cachedUsers = Optional.of(this.userRepository.findAll())
                .orElseThrow(() -> new Exception("Erro ao buscar usuários"));
    }

    @Override
    public User save(User user) throws Exception {
        if (hasKey.test(user)) {
            user.setKey(UUID.randomUUID().toString());
        }
        return Optional.of(this.userRepository.save(user))
                .orElseThrow(() -> new Exception("Houve um erro durante o cadastro de usuário."));
    }

    @Override
    public User update(User user) throws Exception {
        return Optional.of(this.userRepository.saveAndFlush(user))
                .orElseThrow(() -> new Exception("Houve um erro durante a alteração do usuário."));
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

    public List<User> getCachedUsers() {
        return this.cachedUsers;
    }

}
