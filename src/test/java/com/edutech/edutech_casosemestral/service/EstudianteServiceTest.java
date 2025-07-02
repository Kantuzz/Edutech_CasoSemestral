package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Estudiante;
import com.edutech.edutech_casosemestral.repository.EstudianteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EstudianteServiceTest {

    @InjectMocks
    private EstudianteService estudianteService;

    @Mock
    private EstudianteRepository estudianteRepository;

    private Estudiante estudiante;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        estudiante = new Estudiante();
        estudiante.setNombre("Maria");
        estudiante.setCorreo("maria@test.com");
    }

    @Test
    public void testGuardar() {
        when(estudianteRepository.save(estudiante)).thenReturn(estudiante);
        Estudiante result = estudianteService.guardar(estudiante);
        assertEquals("Maria", result.getNombre());
    }

    @Test
    public void testListar() {
        when(estudianteRepository.findAll()).thenReturn(List.of(estudiante));
        List<Estudiante> lista = estudianteService.listar();
        assertEquals(1, lista.size());
    }

    @Test
    public void testActualizar() {
        Estudiante actualizado = new Estudiante();
        actualizado.setNombre("Carla");

        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante));
        when(estudianteRepository.save(any())).thenReturn(actualizado);

        Estudiante result = estudianteService.actualizar(1L, actualizado);
        assertEquals("Carla", result.getNombre());
    }

    @Test
    public void testEliminar() {
        doNothing().when(estudianteRepository).deleteById(1L);
        estudianteService.eliminar(1L);
        verify(estudianteRepository, times(1)).deleteById(1L);
    }
}
