package com.proempresa.h2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_cliente;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "nro_documento")
    private String nro_documento;

    @Column(name = "email")
    private String email;

    @Column(name = "create_at")
    private Date create_at;

    //@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)

    //private List<Venta> ventas;


}
