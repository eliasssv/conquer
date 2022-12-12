package com.conquer.service;

import com.conquer.filter.CityExpensesFilter;
import com.conquer.model.CityExpenses;

import java.util.List;

public interface CityExpensesService {

    List<CityExpenses> findAll();

    CityExpenses getById(Long id);

    List<CityExpenses> findByFilter(CityExpensesFilter filter);
}
