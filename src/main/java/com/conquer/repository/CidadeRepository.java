package com.conquer.repository;

import com.conquer.model.Cidade;
import com.conquer.model.CidadeGasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {}