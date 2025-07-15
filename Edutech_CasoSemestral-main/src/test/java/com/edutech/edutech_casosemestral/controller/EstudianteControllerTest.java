package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Estudiante;
import com.edutech.edutech_casosemestral.service.EstudianteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

public class EstudianteControllerTest {

    @Mock
    private EstudianteService estudianteService;

    @InjectMocks
    private EstudianteController estudianteController;

    public EstudianteControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardar() {
        Estudiante est = new Estudiante();
        est.setNombre("Juan");

        when(estudianteService.guardar(est)).thenReturn(est);

        Estudiante result = estudianteController.guardar(est);
        assertEquals("Juan", result.getNombre());
    }

    @Test
    public void testListar() {
        when(estudianteService.listar()).thenReturn(List.of(new Estudiante()));
        List<Estudiante> lista = estudianteController.listar();
        assertEquals(1, lista.size());
    }

    @Test
    public void testActualizar() {
        Estudiante est = new Estudiante();
        est.setNombre("Pedro");

        when(estudianteService.actualizar(1L, est)).thenReturn(est);

        Estudiante result = estudianteController.actualizar(1L, est);
        assertEquals("Pedro", result.getNombre());
    }

    @Test
    public void testEliminar() {
        doNothing().when(estudianteService).eliminar(1L);
        estudianteController.eliminar(1L);
        verify(estudianteService, times(1)).eliminar(1L);
    }
}
