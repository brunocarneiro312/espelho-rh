package br.com.imasoft.espelhorh.service.impl;

import br.com.imasoft.espelhorh.model.Notificacao;
import br.com.imasoft.espelhorh.repository.NotificacaoRepository;
import br.com.imasoft.espelhorh.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificacaoServiceImpl implements NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    @Autowired
    public NotificacaoServiceImpl(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    @Override
    public Notificacao findById(Integer id) throws Exception {
        return this.notificacaoRepository.findById(id)
                .orElseThrow(Exception::new);
    }

    @Override
    public List<Notificacao> findAll() throws Exception {
        return this.notificacaoRepository.findAll();
    }

    @Override
    public Notificacao save(Notificacao notificacao) throws Exception {
        notificacao.setDataInicio(new Date());
        return this.notificacaoRepository.save(notificacao);
    }

    @Override
    public Notificacao update(Notificacao notificacao) throws Exception {
        return this.notificacaoRepository.saveAndFlush(notificacao);
    }

    @Override
    public Notificacao deleteById(Integer id) throws Exception {
        Notificacao deletedNotificacao = this.findById(id);
        if (deletedNotificacao != null) {
            this.notificacaoRepository.deleteById(id);
            return deletedNotificacao;
        }
        return null;
    }
}
