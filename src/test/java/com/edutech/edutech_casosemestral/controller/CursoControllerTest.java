package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Curso;
import com.edutech.edutech_casosemestral.service.CursoService;
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

public class CursoControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CursoController cursoController;

    @Mock
    private CursoService cursoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cursoController).build();
    }

    @Test
    public void testListar() throws Exception {
        Curso curso = new Curso();
        curso.setTitulo("Test curso");

        when(cursoService.listar()).thenReturn(List.of(curso));

        mockMvc.perform(get("/api/v1/cursos"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGuardar() throws Exception {
        Curso curso = new Curso();
        curso.setTitulo("Nuevo curso");

        when(cursoService.guardar(any(Curso.class))).thenReturn(curso);

        mockMvc.perform(post("/api/v1/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(curso)))
                .andExpect(status().isOk());
    }

    @Test
    public void testActualizar() throws Exception {
        Curso curso = new Curso();
        curso.setTitulo("Actualizado");

        when(cursoService.actualizar(eq(1L), any(Curso.class))).thenReturn(curso);

        mockMvc.perform(put("/api/v1/cursos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(curso)))
                .andExpect(status().isOk());
    }

    @Test
    public void testEliminar() throws Exception {
        doNothing().when(cursoService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/cursos/1"))
                .andExpect(status().isOk());
    }
}
