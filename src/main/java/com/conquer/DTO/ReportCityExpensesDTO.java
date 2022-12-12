package com.conquer.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReportCityExpensesDTO {
    private String startDate;
    private String endDate;
    private List<ReportCityExpensesDetailDTO> cities;
}
