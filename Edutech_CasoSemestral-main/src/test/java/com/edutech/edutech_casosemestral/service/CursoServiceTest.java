package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Curso;
import com.edutech.edutech_casosemestral.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CursoServiceTest {

    @InjectMocks
    private CursoService cursoService;

    @Mock
    private CursoRepository cursoRepository;

    private Curso curso;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        curso = new Curso();
        curso.setTitulo("Curso Test");
        curso.setPrecio(100.0);
    }

    @Test
    public void testGuardar() {
        when(cursoRepository.save(curso)).thenReturn(curso);
        Curso result = cursoService.guardar(curso);
        assertEquals("Curso Test", result.getTitulo());
        verify(cursoRepository, times(1)).save(curso);
    }

    @Test
    public void testListar() {
        List<Curso> lista = List.of(curso);
        when(cursoRepository.findAll()).thenReturn(lista);
        List<Curso> result = cursoService.listar();
        assertEquals(1, result.size());
    }

    @Test
    public void testActualizar() {
        Curso actualizado = new Curso();
        actualizado.setTitulo("Curso Actualizado");

        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
        when(cursoRepository.save(any(Curso.class))).thenReturn(actualizado);

        Curso result = cursoService.actualizar(1L, actualizado);
        assertEquals("Curso Actualizado", result.getTitulo());
    }

    @Test
    public void testEliminar() {
        doNothing().when(cursoRepository).deleteById(1L);
        cursoService.eliminar(1L);
        verify(cursoRepository, times(1)).deleteById(1L);
    }
}
