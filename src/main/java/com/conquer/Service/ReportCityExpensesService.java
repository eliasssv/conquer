package com.conquer.service;

import com.conquer.dto.ReportCityExpensesDTO;

import java.text.ParseException;

public interface ReportCityExpensesService {

    ReportCityExpensesDTO getReportCityExpenses(String startDate, String endDate) throws ParseException;

}
