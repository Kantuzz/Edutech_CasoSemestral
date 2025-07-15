package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Evaluacion;
import com.edutech.edutech_casosemestral.repository.EvaluacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EvaluacionServiceTest {

    @InjectMocks
    private EvaluacionService evaluacionService;

    @Mock
    private EvaluacionRepository evaluacionRepository;

    private Evaluacion evaluacion;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        evaluacion = new Evaluacion();
        evaluacion.setTipo("Diagnóstico");
    }

    @Test
    public void testGuardar() {
        when(evaluacionRepository.save(evaluacion)).thenReturn(evaluacion);
        Evaluacion result = evaluacionService.guardar(evaluacion);
        assertEquals("Diagnóstico", result.getTipo());
    }

    @Test
    public void testListar() {
        when(evaluacionRepository.findAll()).thenReturn(List.of(evaluacion));
        List<Evaluacion> lista = evaluacionService.listar();
        assertEquals(1, lista.size());
    }

    @Test
    public void testActualizar() {
        Evaluacion nueva = new Evaluacion();
        nueva.setTipo("Final");

        when(evaluacionRepository.findById(1L)).thenReturn(Optional.of(evaluacion));
        when(evaluacionRepository.save(any())).thenReturn(nueva);

        Evaluacion result = evaluacionService.actualizar(1L, nueva);
        assertEquals("Final", result.getTipo());
    }

    @Test
    public void testEliminar() {
        doNothing().when(evaluacionRepository).deleteById(1L);
        evaluacionService.eliminar(1L);
        verify(evaluacionRepository, times(1)).deleteById(1L);
    }
}
