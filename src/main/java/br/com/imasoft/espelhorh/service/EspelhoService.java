package br.com.imasoft.espelhorh.service;

import br.com.imasoft.espelhorh.model.Espelho;
import org.springframework.web.multipart.MultipartFile;

public interface EspelhoService extends CrudService<Espelho> {

    Espelho getJson(String espelho, MultipartFile file) throws Exception;

}
