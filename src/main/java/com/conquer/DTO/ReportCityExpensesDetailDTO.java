package com.conquer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportCityExpensesDetailDTO {
    private String cityName;
    private Double value;
}
