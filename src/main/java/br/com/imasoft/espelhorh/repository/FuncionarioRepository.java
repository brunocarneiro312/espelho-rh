package br.com.imasoft.espelhorh.repository;

import br.com.imasoft.espelhorh.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}
