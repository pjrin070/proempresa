package com.proempresa.h2.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.proempresa.h2.model.Cliente;
import com.proempresa.h2.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ClienteController {

  @Autowired
  ClienteService clienteService;

  @GetMapping("/clientes")
  public ResponseEntity<List<Cliente>> getAllClientes( ) {
    try {
      List<Cliente> clientes = new ArrayList<Cliente>();


       clientes =  clienteService.getAllClientes();

      if (clientes.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(clientes, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/clientes/{id}")
  public ResponseEntity<Cliente> getClienteById(@PathVariable("id") long id) {

    try {
     Cliente  clienteData =  clienteService.getClienteById(id);

    if (clienteData!=null) {
      return new ResponseEntity<>(clienteData , HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  } catch (Exception e) {
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  }

  @PostMapping("/clientes")
  public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
    try {
      Cliente _cliente = clienteService.createCliente( cliente);
      return new ResponseEntity<>(_cliente, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/clientes/{id}")
  public ResponseEntity<Cliente> updateCliente(@PathVariable("id") long id, @RequestBody Cliente cliente) {
     Cliente  clienteData = clienteService.updateCliente(id,cliente);

    if (clienteData!=null) {
      return new ResponseEntity<>(clienteData, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/clientes/{id}")
  public ResponseEntity<HttpStatus> deleteCliente(@PathVariable("id") long id) {
    try {
      clienteService.deleteCliente(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
/*
  @DeleteMapping("/clientes")
  public ResponseEntity<HttpStatus> deleteAllClientes() {
    try {
      clienteRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
*/

}
