package br.com.imasoft.espelhorh.controller;

import br.com.imasoft.espelhorh.model.Funcionario;
import br.com.imasoft.espelhorh.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<Funcionario> save(@RequestBody Funcionario request) throws Exception {
        return Optional.of(new ResponseEntity<>(this.funcionarioService.save(request), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> list() throws Exception {
        return Optional.of(new ResponseEntity<>(this.funcionarioService.findAll(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable("id") Integer id) throws Exception {
        return Optional.of(new ResponseEntity<>(this.funcionarioService.findById(id), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable("id") Integer id,
                                              @RequestBody Funcionario request) throws Exception {

        Funcionario f = this.funcionarioService.findById(id);

        assert f != null;

        f.setRg(request.getRg());
        f.setNome(request.getNome());

        return Optional.of(new ResponseEntity<>(this.funcionarioService.update(f), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) throws Exception {

        Funcionario funcionario = this.funcionarioService.deleteById(id);

        if (funcionario != null) {
            return new ResponseEntity<>("Funcionário removido.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Erro ao remover o funcionário.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
