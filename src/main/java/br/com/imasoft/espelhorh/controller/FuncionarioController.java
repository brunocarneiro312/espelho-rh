package br.com.imasoft.espelhorh.controller;

import br.com.imasoft.espelhorh.dto.request.FuncionarioRequest;
import br.com.imasoft.espelhorh.dto.response.FuncionarioResponse;
import br.com.imasoft.espelhorh.model.Funcionario;
import br.com.imasoft.espelhorh.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponse> save(@RequestBody FuncionarioRequest request) throws Exception {

        Funcionario funcionario = new Funcionario.Builder()
                .nome(request.getNome())
                .rg(request.getRg())
                .build();

        Funcionario funcionarioSaved = this.funcionarioService.save(funcionario);

        if (funcionarioSaved == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        FuncionarioResponse response = new FuncionarioResponse(funcionarioSaved);

        return Optional.of(new ResponseEntity<>(response, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponse>> list() throws Exception {

        List<Funcionario> funcionarios = this.funcionarioService.findAll();

        if (funcionarios.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<FuncionarioResponse> response = funcionarios.stream()
                .map(FuncionarioResponse::new)
                .collect(Collectors.toList());

        return Optional.of(new ResponseEntity<>(response, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> findById(@PathVariable("id") Integer id) throws Exception {

        Funcionario recoveredFuncionario = this.funcionarioService.findById(id);

        if (recoveredFuncionario == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return Optional.of(new ResponseEntity<>(new FuncionarioResponse(recoveredFuncionario), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PutMapping
    public ResponseEntity<FuncionarioResponse> update(@RequestBody FuncionarioRequest request) throws Exception {

        Funcionario funcionario = new Funcionario.Builder()
                .nome(request.getNome())
                .rg(request.getRg())
                .build();

        Funcionario updatedFuncionario = this.funcionarioService.update(funcionario);

        if (updatedFuncionario == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return Optional.of(new ResponseEntity<>(new FuncionarioResponse(updatedFuncionario), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> delete(@PathVariable("id") Integer id) throws Exception {

        Funcionario deletedFuncionario = this.funcionarioService.deleteById(id);
        FuncionarioResponse response = new FuncionarioResponse(deletedFuncionario);

        return Optional.of(new ResponseEntity<>(response, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
