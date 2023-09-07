package com.proempresa.h2.controller;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.proempresa.h2.model.Cliente;
import com.proempresa.h2.repository.ClienteRepository;
import com.proempresa.h2.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {
   // @Mock
   // ClienteRepository clienteRepository;

   // @InjectMocks
   // private ClienteController clientecontroller;

    @MockBean
    private ClienteService clienteService;
    @Autowired
    private MockMvc mvc;

    private Cliente cliente;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {

        cliente = new Cliente(2,"Juan", "Perez", "46348228","prueba@gmail.com",java.sql.Date.valueOf(LocalDate.parse("2023-09-06" )));
    }

    @Test
    void getAllClientes() throws Exception {

        when(clienteService.getAllClientes()).thenReturn(Collections.singletonList(cliente));
        mvc.perform(get("/api/clientes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$").isArray());
        //when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));
        //assertNotNull(clientecontroller.getAllClientes());
    }

    @Test
    void getClienteById()  throws Exception{
        when(clienteService.getClienteById(2)).thenReturn(cliente);
        mvc.perform(get("/api/clientes/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombres", is("Juan")))
                .andExpect(jsonPath("$.id_cliente", is(2)))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void createCliente() throws Exception {
        when(clienteService.createCliente(cliente)).thenReturn(cliente);
        mvc.perform(post("/api/clientes")
                                .content(objectMapper.writeValueAsString(cliente))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombres", is("Juan")))
                .andExpect(jsonPath("$.id_cliente", is(2)))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void updateCliente()  throws Exception{
        when(clienteService.updateCliente(cliente.getId_cliente() ,cliente)).thenReturn(cliente);
        mvc.perform(put("/api/clientes/2")
                        .content(objectMapper.writeValueAsString(cliente))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombres", is("Juan")))
                .andExpect(jsonPath("$.id_cliente", is(2)))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void deleteCliente() throws Exception{
        doNothing().when(clienteService).deleteCliente(cliente.getId_cliente());
        mvc.perform(delete("/api/clientes/" + cliente.getId_cliente()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}