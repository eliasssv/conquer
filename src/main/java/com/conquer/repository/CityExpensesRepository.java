package com.conquer.repository;

import com.conquer.model.CityExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CityExpensesRepository extends JpaRepository<CityExpenses, Long> {

    @Query("SELECT ce FROM CityExpenses ce WHERE data between :startDate and :endDate ")
    List<CityExpenses> getByFilter(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );
}