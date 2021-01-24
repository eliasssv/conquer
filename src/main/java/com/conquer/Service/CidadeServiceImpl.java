package com.conquer.service;

import com.conquer.model.Cidade;
import com.conquer.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeServiceImpl implements CidadeService{

    @Autowired
    private CidadeRepository repository;

    public List<Cidade> findAll() {
        return repository.findAll();
    }

    public Cidade getById(Long id) {
        return repository.getOne(id);
    }
}
