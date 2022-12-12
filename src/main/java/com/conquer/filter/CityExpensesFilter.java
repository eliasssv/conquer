package com.conquer.filter;

import lombok.Data;

import java.util.Date;

@Data
public class CityExpensesFilter {

    private Date startDate;
    private Date endDate;
}
