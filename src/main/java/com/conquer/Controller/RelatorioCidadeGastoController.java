package com.conquer.controller;

import com.conquer.dto.RelatorioCidadeGastoDTO;
import com.conquer.service.RelatorioCidadeGastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Calendar;

@Controller
public class RelatorioCidadeGastoController {

    @Autowired
    private RelatorioCidadeGastoService relatorioCidadeGastoService;

    @GetMapping("/relatorio-cidade-gasto")
    public ResponseEntity getRelatorioCidadeGasto(
            @RequestParam(value = "data_inicial") String dataInicial,
            @RequestParam(value = "data_final") String dataFinal
    ) throws ParseException {
        RelatorioCidadeGastoDTO relatorioCidadeGastoDTO = relatorioCidadeGastoService.getRelatorioCidadeGasto(dataInicial,dataFinal);
        return ResponseEntity.ok().body(relatorioCidadeGastoDTO);
    }
}
