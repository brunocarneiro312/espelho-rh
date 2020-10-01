package br.com.imasoft.espelhorh.service.impl;

import br.com.imasoft.espelhorh.model.Funcionario;
import br.com.imasoft.espelhorh.repository.FuncionarioRepository;
import br.com.imasoft.espelhorh.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public Funcionario findById(Integer id) throws Exception {
        return this.funcionarioRepository.findById(id)
                .orElseThrow(Exception::new);
    }

    @Override
    public List<Funcionario> findAll() throws Exception {
        return this.funcionarioRepository.findAll();
    }

    @Override
    public Funcionario save(Funcionario funcionario) throws Exception {
        assert funcionario.getUser() != null;
        funcionario.getUser().setKey(UUID.randomUUID().toString());
        return this.funcionarioRepository.save(funcionario);
    }

    @Override
    public Funcionario update(Funcionario funcionario) throws Exception {
        return Optional.of(this.funcionarioRepository.saveAndFlush(funcionario))
                .orElseThrow(Exception::new);
    }

    @Override
    public Funcionario deleteById(Integer id) throws Exception {
        Funcionario funcionarioDeleted = this.findById(id);
        if (funcionarioDeleted != null) {
            this.funcionarioRepository.delete(funcionarioDeleted);
        }
        return funcionarioDeleted;
    }
}
