package com.proempresa.h2.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "productos")
@Data
public class Producto {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_producto;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "estado")
    private byte estado;
}
