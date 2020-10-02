package br.com.imasoft.espelhorh.controller;

import br.com.imasoft.espelhorh.model.Espelho;
import br.com.imasoft.espelhorh.service.EspelhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(
            consumes =  { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE },
            produces =  { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Espelho> save(@RequestParam("espelho") String espelhoStr,
                                        @RequestParam("file") MultipartFile file) throws Exception {

        Espelho espelho = this.espelhoService.getJson(espelhoStr, file);

        return Optional.of(new ResponseEntity<>(this.espelhoService.save(espelho), HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PutMapping
    public ResponseEntity<Espelho> update(@RequestBody Espelho espelho) throws Exception {
        return Optional.of(new ResponseEntity<>(this.espelhoService.update(espelho), HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) throws Exception {

        Espelho espelho = this.espelhoService.findById(id);

        if (espelho != null) {
            return new ResponseEntity<>("Espelho de ponto removido.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Erro ao remover espelho de ponto.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
