package com.conquer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtratoCartao {
    private String id;
    private String dataTransacao;
    private String valorTransacao;
    private ExtratoCartaoEstabelecimento estabelecimento;
}
