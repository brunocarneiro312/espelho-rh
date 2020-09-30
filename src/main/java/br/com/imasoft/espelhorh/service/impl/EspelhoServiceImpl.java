package br.com.imasoft.espelhorh.service.impl;

import br.com.imasoft.espelhorh.model.Espelho;
import br.com.imasoft.espelhorh.repository.EspelhoRepository;
import br.com.imasoft.espelhorh.service.EspelhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspelhoServiceImpl implements EspelhoService {

    private final EspelhoRepository espelhoRepository;

    @Autowired
    public EspelhoServiceImpl(EspelhoRepository espelhoRepository) {
        this.espelhoRepository = espelhoRepository;
    }

    @Override
    public Espelho findById(Integer id) throws Exception {
        return this.espelhoRepository.findById(id)
                .orElseThrow(Exception::new);
    }

    @Override
    public List<Espelho> findAll() throws Exception {
        return this.espelhoRepository.findAll();
    }

    @Override
    public Espelho save(Espelho espelho) throws Exception {
        return this.espelhoRepository.save(espelho);
    }

    @Override
    public Espelho update(Espelho espelho) throws Exception {
        return this.espelhoRepository.saveAndFlush(espelho);
    }

    @Override
    public Espelho deleteById(Integer id) throws Exception {
        Espelho espelhoDeleted = this.findById(id);
        if (espelhoDeleted != null) {
            this.espelhoRepository.deleteById(id);
            return espelhoDeleted;
        }
        return null;
    }
}
