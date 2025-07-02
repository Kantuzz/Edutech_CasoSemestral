package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Contenido;
import com.edutech.edutech_casosemestral.repository.ContenidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContenidoServiceTest {

    @InjectMocks
    private ContenidoService contenidoService;

    @Mock
    private ContenidoRepository contenidoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListar() {
        Contenido contenido = new Contenido();
        contenido.setTitulo("Contenido de prueba");

        when(contenidoRepository.findAll()).thenReturn(List.of(contenido));

        List<Contenido> resultado = contenidoService.listar();

        assertEquals(1, resultado.size());
        verify(contenidoRepository, times(1)).findAll();
    }

    @Test
    public void testGuardar() {
        Contenido contenido = new Contenido();
        contenido.setTitulo("Nuevo contenido");

        when(contenidoRepository.save(any(Contenido.class))).thenReturn(contenido);

        Contenido guardado = contenidoService.guardar(contenido);

        assertNotNull(guardado);
        assertEquals("Nuevo contenido", guardado.getTitulo());
        verify(contenidoRepository, times(1)).save(contenido);
    }

    @Test
    public void testActualizar() {
        Contenido contenidoExistente = new Contenido();
        contenidoExistente.setId(1L);
        contenidoExistente.setTitulo("Original");

        Contenido contenidoActualizado = new Contenido();
        contenidoActualizado.setTitulo("Actualizado");

        when(contenidoRepository.findById(1L)).thenReturn(Optional.of(contenidoExistente));
        when(contenidoRepository.save(any(Contenido.class))).thenReturn(contenidoExistente);

        Contenido resultado = contenidoService.actualizar(1L, contenidoActualizado);

        assertEquals("Actualizado", resultado.getTitulo());
        verify(contenidoRepository, times(1)).findById(1L);
        verify(contenidoRepository, times(1)).save(contenidoExistente);
    }

    @Test
    public void testEliminar() {
        Long id = 1L;

        doNothing().when(contenidoRepository).deleteById(id);

        contenidoService.eliminar(id);

        verify(contenidoRepository, times(1)).deleteById(id);
    }
}
