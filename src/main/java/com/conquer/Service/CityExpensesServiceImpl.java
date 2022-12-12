package com.conquer.service;

import com.conquer.filter.CityExpensesFilter;
import com.conquer.model.CityExpenses;
import com.conquer.repository.CityExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityExpensesServiceImpl implements CityExpensesService {

    @Autowired
    private CityExpensesRepository repository;


    @Override
    public List<CityExpenses> findAll() {
        return repository.findAll();
    }

    @Override
    public CityExpenses getById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<CityExpenses> findByFilter(CityExpensesFilter filter) {
        return repository.getByFilter(filter.getStartDate(), filter.getEndDate());
    }
}
