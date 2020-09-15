package br.com.imasoft.springsectemplate.config;

import br.com.imasoft.springsectemplate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Primary
@Service
public class MyUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return Optional.of(new MyUserDetails(this.userRepository.findUserByEmail(email)))
                .orElseThrow(() -> new UsernameNotFoundException("Credenciais inválidas"));
    }
}
