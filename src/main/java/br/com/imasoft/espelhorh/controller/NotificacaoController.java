package br.com.imasoft.espelhorh.controller;

import br.com.imasoft.espelhorh.model.Notificacao;
import br.com.imasoft.espelhorh.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/notificacao")
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    @Autowired
    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @PostMapping
    public ResponseEntity<Notificacao> save(@RequestBody Notificacao notificacao) throws Exception {
        return Optional.of(new ResponseEntity<>(this.notificacaoService.save(notificacao), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping
    public ResponseEntity<List<Notificacao>> list() throws Exception {
        return Optional.of(new ResponseEntity<>(this.notificacaoService.findAll(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacao> findById(@PathVariable("id") Integer id) throws Exception {
        return Optional.of(new ResponseEntity<>(this.notificacaoService.findById(id), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacao> update(@PathVariable("id") Integer id,
                                              @RequestBody Notificacao notificacao) throws Exception {

        Notificacao n = this.notificacaoService.findById(id);

        assert n != null;

        n.setText(notificacao.getText());
        n.setDataInicio(notificacao.getDataInicio());
        n.setDataFim(notificacao.getDataFim());

        return Optional.of(new ResponseEntity<>(this.notificacaoService.update(n), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) throws Exception {

        Notificacao notificacao = this.notificacaoService.findById(id);

        if (notificacao != null) {
            return new ResponseEntity<>("Notificação removida.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Erro ao remover notificação.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
