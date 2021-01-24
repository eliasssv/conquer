package com.conquer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_cidade_gasto")
public class CidadeGasto {
    @Id
    private Long id;
    private Date data;
    @ManyToOne
    private Cidade cidade;
    private Double valor;
}

