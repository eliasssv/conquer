package com.conquer.repository;

import com.conquer.filter.CidadeGastoFilter;
import com.conquer.model.CidadeGasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CidadeGastoRepository extends JpaRepository<CidadeGasto, Long> {

    @Query("SELECT cg FROM CidadeGasto cg WHERE data between :dataInicial and :dataFinal ")
    List<CidadeGasto> getByFilter(
            @Param("dataInicial") Date dataInicial,
            @Param("dataFinal") Date dataFinal
    );
}