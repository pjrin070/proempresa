package com.proempresa.h2.services;

import com.proempresa.h2.model.Cliente;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface ClienteService {

    public   List<Cliente>  getAllClientes( ) ;
    public Cliente  getClienteById(long id) ;
    public  Cliente  createCliente( Cliente cliente);
    public Cliente  updateCliente( long id,Cliente cliente);
    public void deleteCliente(  long id);
}
