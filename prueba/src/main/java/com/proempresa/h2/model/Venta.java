package com.proempresa.h2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_venta;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "serie")
    private String serie;
    @Column(name = "numero")
    private String numero;
    @Column(name = "total")
    private Double total;

    @ManyToOne()
    @JoinColumn(name = "client_id" )
    private Cliente cliente;


}
