package com.conquer.service;

import com.conquer.model.Cidade;

import java.util.List;

public interface CidadeService {

    List<Cidade> findAll();

    Cidade getById(Long id);
}
