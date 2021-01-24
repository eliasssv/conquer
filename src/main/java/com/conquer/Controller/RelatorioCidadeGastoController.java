package com.conquer.controller;

import com.conquer.model.Cidade;
import com.conquer.service.CidadeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RelatorioCidadeGastoController {

    @Autowired
    private CidadeServiceImpl cidadeService;

    @GetMapping("/relatorio-cidade-gasto")
    public ResponseEntity getRelatorioCidadeGasto() {
        List<Cidade> cidades = cidadeService.findAll();
        ResponseEntity response = ResponseEntity.ok().body(cidades);
        return response;
    }
}
