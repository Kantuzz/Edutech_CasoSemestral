package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Instructor;
import com.edutech.edutech_casosemestral.repository.InstructorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InstructorServiceTest {

    @InjectMocks
    private InstructorService instructorService;

    @Mock
    private InstructorRepository instructorRepository;

    private Instructor instructor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        instructor = new Instructor();
        instructor.setNombre("Carlos");
        instructor.setCorreo("carlos@test.com");
    }

    @Test
    public void testGuardar() {
        when(instructorRepository.save(instructor)).thenReturn(instructor);
        Instructor result = instructorService.guardar(instructor);
        assertEquals("Carlos", result.getNombre());
    }

    @Test
    public void testListar() {
        when(instructorRepository.findAll()).thenReturn(List.of(instructor));
        List<Instructor> lista = instructorService.listar();
        assertEquals(1, lista.size());
    }

    @Test
    public void testActualizar() {
        Instructor actualizado = new Instructor();
        actualizado.setNombre("Daniela");

        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));
        when(instructorRepository.save(any())).thenReturn(actualizado);

        Instructor result = instructorService.actualizar(1L, actualizado);
        assertEquals("Daniela", result.getNombre());
    }

    @Test
    public void testEliminar() {
        doNothing().when(instructorRepository).deleteById(1L);
        instructorService.eliminar(1L);
        verify(instructorRepository, times(1)).deleteById(1L);
    }
}
