package com.conquer.DTO;

import java.util.List;

public class RelatorioCidadeGastoDTO {
    private String anoMesInicial;
    private String anoMesFinal;
    private List<RelatorioCidadeGastoDetalheDTO> detalhe;

    public String getAnoMesInicial() {
        return anoMesInicial;
    }

    public void setAnoMesInicial(String anoMesInicial) {
        this.anoMesInicial = anoMesInicial;
    }

    public String getAnoMesFinal() {
        return anoMesFinal;
    }

    public void setAnoMesFinal(String anoMesFinal) {
        this.anoMesFinal = anoMesFinal;
    }

    public List<RelatorioCidadeGastoDetalheDTO> getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(List<RelatorioCidadeGastoDetalheDTO> detalhe) {
        this.detalhe = detalhe;
    }
}
