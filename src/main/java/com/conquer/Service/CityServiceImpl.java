package com.conquer.service;

import com.conquer.model.City;
import com.conquer.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository repository;

    public List<City> findAll() {
        return repository.findAll();
    }

    public City getById(Long id) {
        return repository.getOne(id);
    }
}
