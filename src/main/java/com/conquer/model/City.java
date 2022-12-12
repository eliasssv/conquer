package com.conquer.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Table(name = "tb_city")
public class City {
    @Id
    private Long id;
    private String name;
    private String uf;

}
