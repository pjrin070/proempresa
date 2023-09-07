package com.proempresa.h2.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "detalle_ventas")
@Data
public class Detalle_venta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_detalle;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "sub_total")
    private Double sub_total;
}
