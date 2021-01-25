package com.conquer.service;

import com.conquer.dto.RelatorioCidadeGastoDTO;

import java.text.ParseException;

public interface RelatorioCidadeGastoService {

    RelatorioCidadeGastoDTO getRelatorioCidadeGasto(String dataInicial, String dataFinal) throws ParseException;

}
