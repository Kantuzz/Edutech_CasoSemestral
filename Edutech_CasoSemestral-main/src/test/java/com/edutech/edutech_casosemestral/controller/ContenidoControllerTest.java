package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Contenido;
import com.edutech.edutech_casosemestral.service.ContenidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ContenidoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ContenidoService contenidoService;

    @InjectMocks
    private ContenidoController contenidoController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contenidoController).build();
    }

    @Test
    public void testListarContenido() throws Exception {
        Contenido contenido = new Contenido();
        contenido.setTitulo("Contenido de prueba");

        when(contenidoService.listar()).thenReturn(List.of(contenido));

        mockMvc.perform(get("/api/v1/contenidos"))
                .andExpect(status().isOk());

        verify(contenidoService, times(1)).listar();
    }

    @Test
    public void testGuardarContenido() throws Exception {
        Contenido contenido = new Contenido();
        contenido.setTitulo("Nuevo contenido");

        when(contenidoService.guardar(any(Contenido.class))).thenReturn(contenido);

        mockMvc.perform(post("/api/v1/contenidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contenido)))
                .andExpect(status().isOk());

        verify(contenidoService, times(1)).guardar(any(Contenido.class));
    }

    @Test
    public void testActualizarContenido() throws Exception {
        Contenido contenido = new Contenido();
        contenido.setTitulo("Contenido actualizado");

        when(contenidoService.actualizar(eq(1L), any(Contenido.class))).thenReturn(contenido);

        mockMvc.perform(put("/api/v1/contenidos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contenido)))
                .andExpect(status().isOk());

        verify(contenidoService, times(1)).actualizar(eq(1L), any(Contenido.class));
    }

    @Test
    public void testEliminarContenido() throws Exception {
        doNothing().when(contenidoService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/contenidos/1"))
                .andExpect(status().isOk());

        verify(contenidoService, times(1)).eliminar(1L);
    }
}
