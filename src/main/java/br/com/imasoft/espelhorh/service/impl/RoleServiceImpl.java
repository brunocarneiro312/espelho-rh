package br.com.imasoft.espelhorh.service.impl;

import br.com.imasoft.espelhorh.model.Role;
import br.com.imasoft.espelhorh.repository.RoleRepository;
import br.com.imasoft.espelhorh.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author brunocarneiro
 */
@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findById(Integer id) throws Exception {
        try {
            return roleRepository.findById(id).get();
        }
        catch (Exception e) {
            throw new Exception("Houve um erro durante a busca da role.", e);
        }
    }

    @Override
    public List<Role> findAll() throws Exception {
        try {
            return roleRepository.findAll();
        }
        catch (Exception e) {
            throw new Exception("Houve um erro durante a listagem das roles.", e);
        }
    }

    @Override
    public Role save(Role role) throws Exception {
        try {
            return roleRepository.save(role);
        }
        catch (Exception e) {
            throw new Exception("Houve um erro ao cadastrar a role.", e);
        }
    }

    @Override
    public Role update(Role role) throws Exception {
        try {
            return roleRepository.saveAndFlush(role);
        }
        catch (Exception e) {
            throw new Exception("Houve um erro durante a alteração da role.", e);
        }
    }

    @Override
    public Role deleteById(Integer id) throws Exception {
        try {
            Role roleDeleted = roleRepository.findById(id)
                    .orElseThrow(Exception::new);
            roleRepository.delete(roleDeleted);
            return roleDeleted;
        }
        catch (Exception e) {
            throw new Exception("Houve um erro durante a remoção da role.", e);
        }
    }

    @Override
    public Role findByRoleName(String roleName) throws Exception {
        return this.roleRepository.findByRoleName(roleName);
    }

    @Override
    @Transactional
    public Role deleteByRoleName(String roleName) throws Exception {
        Role roleDeleted = this.findByRoleName(roleName);
        if (roleDeleted != null) {
            this.roleRepository.deleteByRoleName(roleName);
            return roleDeleted;
        }
        throw new Exception("Não foi possível deletar a role.");
    }
}
