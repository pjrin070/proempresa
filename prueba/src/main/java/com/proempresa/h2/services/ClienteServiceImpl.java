package com.proempresa.h2.services;

import com.proempresa.h2.model.Cliente;
import com.proempresa.h2.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        clienteRepository.findAll().forEach(clientes::add);
        return clientes;
    }

    @Override
    public  Cliente  getClienteById(long id) {

        Optional<Cliente> clienteData = clienteRepository.findById(id);
        if(clienteData.isPresent()){
            return clienteData.get();
        }else{
            return null;
        }
    }

    @Override
    public  Cliente  createCliente(Cliente cliente) {
         Cliente clienteData = clienteRepository.save(new Cliente(cliente.getId_cliente(),cliente.getNombres(), cliente.getApellidos(), cliente.getNro_documento(),cliente.getEmail(), Date.valueOf(LocalDate.now())));
        return clienteData;
    }

    @Override
    public  Cliente  updateCliente(long id,Cliente cliente) {
        Optional<Cliente> clienteData = clienteRepository.findById(id);
        if (clienteData.isPresent()) {
            Cliente _cliente = clienteData.get();
            _cliente.setNombres(cliente.getNombres());
            _cliente.setApellidos(cliente.getApellidos());
            _cliente.setNro_documento(cliente.getNro_documento());
            _cliente.setEmail(cliente.getEmail());
            _cliente.setCreate_at(cliente.getCreate_at());
            return   clienteRepository.save(_cliente) ;
        } else {
            return null;
        }

    }

    @Override
    public void deleteCliente(long id) {
        clienteRepository.deleteById(id);
    }
}
