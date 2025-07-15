package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Soporte;
import com.edutech.edutech_casosemestral.service.SoporteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SoporteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SoporteService soporteService;

    @InjectMocks
    private SoporteController soporteController;

    public SoporteControllerTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(soporteController).build();
    }

    @Test
    void testListar() throws Exception {
        Soporte s = new Soporte();
        s.setDescripcion("Prueba");
        when(soporteService.listar()).thenReturn(Arrays.asList(s));

        mockMvc.perform(get("/api/v1/soportes"))
                .andExpect(status().isOk());
    }

    @Test
    void testGuardar() throws Exception {
        Soporte s = new Soporte();
        s.setDescripcion("Soporte nuevo");

        when(soporteService.guardar(s)).thenReturn(s);

        mockMvc.perform(post("/api/v1/soportes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descripcion\":\"Soporte nuevo\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testActualizar() throws Exception {
        Soporte s = new Soporte();
        s.setDescripcion("Actualizado");

        when(soporteService.actualizar(1L, s)).thenReturn(s);

        mockMvc.perform(put("/api/v1/soportes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descripcion\":\"Actualizado\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testEliminar() throws Exception {
        doNothing().when(soporteService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/soportes/1"))
                .andExpect(status().isOk());
    }
}
