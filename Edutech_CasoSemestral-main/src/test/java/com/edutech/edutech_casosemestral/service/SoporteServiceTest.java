package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Soporte;
import com.edutech.edutech_casosemestral.repository.SoporteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SoporteServiceTest {

    @Mock
    private SoporteRepository soporteRepository;

    @InjectMocks
    private SoporteService soporteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {
        Soporte s = new Soporte();
        s.setDescripcion("Consulta técnica");
        when(soporteRepository.findAll()).thenReturn(Arrays.asList(s));

        List<Soporte> resultado = soporteService.listar();
        assertEquals(1, resultado.size());
        assertEquals("Consulta técnica", resultado.get(0).getDescripcion());
    }

    @Test
    void testGuardar() {
        Soporte s = new Soporte();
        s.setDescripcion("Guardar test");
        when(soporteRepository.save(s)).thenReturn(s);

        Soporte guardado = soporteService.guardar(s);
        assertEquals("Guardar test", guardado.getDescripcion());
    }

    @Test
    void testActualizar() {
        Soporte s = new Soporte();
        s.setDescripcion("Antes");
        when(soporteRepository.save(s)).thenReturn(s);

        Soporte actualizado = soporteService.actualizar(1L, s);
        assertEquals(1L, actualizado.getId());
    }

    @Test
    void testEliminar() {
        Long id = 1L;
        doNothing().when(soporteRepository).deleteById(id);
        soporteService.eliminar(id);
        verify(soporteRepository, times(1)).deleteById(id);
    }
}
