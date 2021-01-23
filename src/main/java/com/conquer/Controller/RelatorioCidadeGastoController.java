package com.conquer.Controller;

import com.conquer.DTO.RelatorioCidadeGastoDTO;
import com.conquer.Entity.CidadeGasto;
import com.conquer.Service.RelatorioCidadeGastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
public class RelatorioCidadeGastoController {

    @Autowired
    RelatorioCidadeGastoService relatorioCidadeGastoService;

    @RequestMapping("/api/relatorio-gastos")
    public RelatorioCidadeGastoDTO getMarsReport(
            @RequestParam(value = "mes_ano_inicial") String mesAnoInicial,
            @RequestParam(value = "mes_ano_final") String mesAnoFinal) {
        return relatorioCidadeGastoService.getRelatorioCidadeGastoService(mesAnoInicial, mesAnoFinal);
    }
}
