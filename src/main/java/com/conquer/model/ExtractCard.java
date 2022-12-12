package com.conquer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractCard {
    private String id;
    private String date;
    private String value;
    private ExtractCardPlace place;
}
