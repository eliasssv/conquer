package com.conquer.controller;

import com.conquer.dto.ReportCityExpensesDTO;
import com.conquer.service.ReportCityExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

@Controller
public class ReportCityExpensesController {

    @Autowired
    private ReportCityExpensesService reportCityExpensesService;

    @GetMapping("/report-city-expenses")
    public ResponseEntity getReportCityExpenses(
            @RequestParam(value = "start_date") String startDate,
            @RequestParam(value = "end_date") String endDate
    ) throws ParseException {
        ReportCityExpensesDTO reportCityExpensesDTO = reportCityExpensesService.getReportCityExpenses(startDate, endDate);
        return ResponseEntity.ok().body(reportCityExpensesDTO);
    }
}
