package com.conquer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtratoCartaoMunicipio {
    private String codigoIBGE;
    private String nomeIBGE;
    
}
