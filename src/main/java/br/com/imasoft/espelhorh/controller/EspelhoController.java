package br.com.imasoft.espelhorh.controller;

import br.com.imasoft.espelhorh.model.Espelho;
import br.com.imasoft.espelhorh.service.EspelhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/espelho")
public class EspelhoController {

    private final EspelhoService espelhoService;

    @Autowired
    public EspelhoController(EspelhoService espelhoService) {
        this.espelhoService = espelhoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Espelho> findById(@PathVariable("id") Integer id) throws Exception {
        return Optional.of(new ResponseEntity<>(this.espelhoService.findById(id), HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping
    public ResponseEntity<List<Espelho>> findAll() throws Exception {
        return Optional.of(new ResponseEntity<>(this.espelhoService.findAll(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PostMapping
    public ResponseEntity<Espelho> save(@RequestBody Espelho espelho) throws Exception {
        return Optional.of(new ResponseEntity<>(this.espelhoService.save(espelho), HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PutMapping
    public ResponseEntity<Espelho> update(@RequestBody Espelho espelho) throws Exception {
        return Optional.of(new ResponseEntity<>(this.espelhoService.update(espelho), HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Espelho> delete(@PathVariable("id") Integer id) throws Exception {
        return Optional.of(new ResponseEntity<>(this.espelhoService.deleteById(id), HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
