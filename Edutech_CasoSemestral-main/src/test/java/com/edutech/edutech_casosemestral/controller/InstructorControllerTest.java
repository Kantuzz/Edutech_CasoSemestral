package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Instructor;
import com.edutech.edutech_casosemestral.service.InstructorService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class InstructorControllerTest {

    @Mock
    private InstructorService instructorService;

    @InjectMocks
    private InstructorController instructorController;

    public InstructorControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardar() {
        Instructor ins = new Instructor();
        ins.setNombre("Luis");

        when(instructorService.guardar(ins)).thenReturn(ins);

        Instructor result = instructorController.guardar(ins);
        assertEquals("Luis", result.getNombre());
    }

    @Test
    public void testListar() {
        when(instructorService.listar()).thenReturn(List.of(new Instructor()));
        List<Instructor> lista = instructorController.listar();
        assertEquals(1, lista.size());
    }

    @Test
    public void testActualizar() {
        Instructor ins = new Instructor();
        ins.setNombre("Ana");

        when(instructorService.actualizar(1L, ins)).thenReturn(ins);

        Instructor result = instructorController.actualizar(1L, ins);
        assertEquals("Ana", result.getNombre());
    }

    @Test
    public void testEliminar() {
        doNothing().when(instructorService).eliminar(1L);
        instructorController.eliminar(1L);
        verify(instructorService, times(1)).eliminar(1L);
    }
}
