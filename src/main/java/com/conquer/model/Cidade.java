package com.conquer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_cidade")
public class Cidade {
    @Id
    private Long id;
    private String nome;
    private String uf;

    public Cidade(Long id) {
        this.id = id;
    }
}
