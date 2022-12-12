package com.conquer.service;

import com.conquer.model.City;

import java.util.List;

public interface CityService {

    List<City> findAll();

    City getById(Long id);
}
