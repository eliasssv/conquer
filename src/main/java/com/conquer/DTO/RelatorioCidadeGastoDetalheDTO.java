package com.conquer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RelatorioCidadeGastoDetalheDTO {
    private String nomeCidade;
    private Double valor;
}
