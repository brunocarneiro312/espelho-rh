package br.com.imasoft.espelhorh.service;

import java.io.Serializable;
import java.util.List;

/**
 * @author brunocarneiro
 */
public interface CrudService<T extends Serializable> {

    T findById(Integer id) throws Exception;

    List<T> findAll() throws Exception;

    T save(T t) throws Exception;

    T update(T t) throws Exception;

    T deleteById(Integer id) throws Exception;

}
