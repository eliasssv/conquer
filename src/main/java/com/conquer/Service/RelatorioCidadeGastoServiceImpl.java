package com.conquer.service;

import com.conquer.dto.RelatorioCidadeGastoDTO;
import com.conquer.repository.CidadeGastoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RelatorioCidadeGastoServiceImpl implements RelatorioCidadeGastoService {

    @Autowired
    private CidadeGastoRepository repository;

    public RelatorioCidadeGastoDTO getRelatorioCidadeGasto(String anoMesInicial, String anoMesFinal) {
        return null;
    }
}
