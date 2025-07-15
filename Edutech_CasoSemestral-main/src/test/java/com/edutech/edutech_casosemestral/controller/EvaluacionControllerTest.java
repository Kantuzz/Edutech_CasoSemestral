package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Evaluacion;
import com.edutech.edutech_casosemestral.service.EvaluacionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EvaluacionControllerTest {

    @Mock
    private EvaluacionService evaluacionService;

    @InjectMocks
    private EvaluacionController evaluacionController;

    public EvaluacionControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardar() {
        Evaluacion eval = new Evaluacion();
        eval.setTipo("Final");

        when(evaluacionService.guardar(eval)).thenReturn(eval);

        Evaluacion result = evaluacionController.guardar(eval);
        assertEquals("Final", result.getTipo());
    }

    @Test
    public void testListar() {
        when(evaluacionService.listar()).thenReturn(List.of(new Evaluacion()));
        List<Evaluacion> lista = evaluacionController.listar();
        assertEquals(1, lista.size());
    }

    @Test
    public void testActualizar() {
        Evaluacion eval = new Evaluacion();
        eval.setTipo("Parcial");

        when(evaluacionService.actualizar(1L, eval)).thenReturn(eval);

        Evaluacion result = evaluacionController.actualizar(1L, eval);
        assertEquals("Parcial", result.getTipo());
    }

    @Test
    public void testEliminar() {
        doNothing().when(evaluacionService).eliminar(1L);
        evaluacionController.eliminar(1L);
        verify(evaluacionService, times(1)).eliminar(1L);
    }
}
