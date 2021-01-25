package com.conquer.dto;

import lombok.Data;

import java.util.List;

@Data
public class RelatorioCidadeGastoDTO {
    private String dataInicial;
    private String dataFinal;
    private List<RelatorioCidadeGastoDetalheDTO> cidades;
}
