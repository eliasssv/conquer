package com.conquer.service;

import com.conquer.filter.CidadeGastoFilter;
import com.conquer.model.Cidade;
import com.conquer.model.CidadeGasto;
import com.conquer.repository.CidadeGastoRepository;
import com.conquer.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeGastoServiceImpl implements CidadeGastoService {

    @Autowired
    private CidadeGastoRepository repository;


    @Override
    public List<CidadeGasto> findAll() {
        return repository.findAll();
    }

    @Override
    public CidadeGasto getById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<CidadeGasto> findByFilter(CidadeGastoFilter filter) {
        return repository.getByFilter(filter.getDataInicial(), filter.getDataFinal());
    }
}
