package com.conquer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractCardCity {
    private String ibgeCode;
    private String ibgeName;
    
}
