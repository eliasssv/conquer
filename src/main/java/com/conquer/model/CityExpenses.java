package com.conquer.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "tb_city_expenses")
public class CityExpenses {
    @Id
    private Long id;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    private Double value;
}

