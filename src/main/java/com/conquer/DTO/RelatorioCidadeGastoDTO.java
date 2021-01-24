package com.conquer.dto;

import lombok.Data;

import java.util.List;

@Data
public class RelatorioCidadeGastoDTO {
    private String anoMesInicial;
    private String anoMesFinal;
    private List<RelatorioCidadeGastoDetalheDTO> cidades;
}
