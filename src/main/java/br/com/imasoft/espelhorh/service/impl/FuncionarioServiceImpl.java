package br.com.imasoft.espelhorh.service.impl;

import br.com.imasoft.espelhorh.enums.RoleEnum;
import br.com.imasoft.espelhorh.model.Funcionario;
import br.com.imasoft.espelhorh.model.Role;
import br.com.imasoft.espelhorh.repository.FuncionarioRepository;
import br.com.imasoft.espelhorh.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
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
        return Optional.of(this.funcionarioRepository.findAll())
                .orElseThrow(Exception::new);
    }

    @Override
    public Funcionario save(Funcionario funcionario) throws Exception {

        assert funcionario.getUser() != null;

        List<Role> roles = new ArrayList<>(Collections.singletonList(new Role.Builder()
                .id(RoleEnum.ROLE_COMMON.getId())
                .build()));

        funcionario.getUser().setRoles(roles);
        funcionario.getUser().setKey(UUID.randomUUID().toString());

        return Optional.of(this.funcionarioRepository.save(funcionario))
                .orElseThrow(Exception::new);
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
