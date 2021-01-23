package com.conquer.DTO;

public class RelatorioCidadeGastoDetalheDTO {
    private String nomeCidade;
    private Double valor;

    public RelatorioCidadeGastoDetalheDTO(String nomeCidade, Double valor) {
        this.nomeCidade = nomeCidade;
        this.valor = valor;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
