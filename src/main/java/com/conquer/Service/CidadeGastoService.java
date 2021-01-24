package com.conquer.service;

import com.conquer.filter.CidadeGastoFilter;
import com.conquer.model.Cidade;
import com.conquer.model.CidadeGasto;

import java.util.List;

public interface CidadeGastoService {

    List<CidadeGasto> findAll();

    CidadeGasto getById(Long id);

    List<CidadeGasto> findByFilter(CidadeGastoFilter filter);
}
