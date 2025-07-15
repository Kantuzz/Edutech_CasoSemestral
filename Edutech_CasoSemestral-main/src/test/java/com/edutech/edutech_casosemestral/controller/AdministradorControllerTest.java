package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Administrador;
import com.edutech.edutech_casosemestral.service.AdministradorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AdministradorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AdministradorService administradorService;

    @InjectMocks
    private AdministradorController administradorController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(administradorController).build();
    }

    @Test
    public void testListar() throws Exception {
        Administrador admin1 = new Administrador();
        admin1.setId(1L);
        admin1.setNombre("Admin Uno");

        Administrador admin2 = new Administrador();
        admin2.setId(2L);
        admin2.setNombre("Admin Dos");

        List<Administrador> lista = Arrays.asList(admin1, admin2);

        when(administradorService.listar()).thenReturn(lista);

        mockMvc.perform(get("/api/v1/administradores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void testGuardar() throws Exception {
        Administrador admin = new Administrador();
        admin.setId(1L);
        admin.setNombre("Nuevo Admin");

        when(administradorService.guardar(any(Administrador.class))).thenReturn(admin);

        mockMvc.perform(post("/api/v1/administradores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(admin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Nuevo Admin"));
    }

    @Test
    public void testActualizar() throws Exception {
        Administrador admin = new Administrador();
        admin.setId(1L);
        admin.setNombre("Actualizado");

        when(administradorService.actualizar(eq(1L), any(Administrador.class))).thenReturn(admin);

        mockMvc.perform(put("/api/v1/administradores/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(admin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Actualizado"));
    }

    @Test
    public void testEliminar() throws Exception {
        doNothing().when(administradorService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/administradores/1"))
                .andExpect(status().isOk());

        verify(administradorService, times(1)).eliminar(1L);
    }
}
