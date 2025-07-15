package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Notificacion;
import com.edutech.edutech_casosemestral.repository.NotificacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NotificacionServiceTest {

    @InjectMocks
    private NotificacionService notificacionService;

    @Mock
    private NotificacionRepository notificacionRepository;

    private Notificacion notificacion;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        notificacion = new Notificacion();
        notificacion.setMensaje("Prueba");
    }

    @Test
    public void testGuardar() {
        when(notificacionRepository.save(notificacion)).thenReturn(notificacion);
        Notificacion result = notificacionService.guardar(notificacion);
        assertEquals("Prueba", result.getMensaje());
    }

    @Test
    public void testListar() {
        when(notificacionRepository.findAll()).thenReturn(List.of(notificacion));
        List<Notificacion> lista = notificacionService.listar();
        assertEquals(1, lista.size());
    }

    @Test
    public void testActualizar() {
        Notificacion nueva = new Notificacion();
        nueva.setMensaje("Actualizada");

        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notificacion));
        when(notificacionRepository.save(any())).thenReturn(nueva);

        Notificacion result = notificacionService.actualizar(1L, nueva);
        assertEquals("Actualizada", result.getMensaje());
    }

    @Test
    public void testEliminar() {
        doNothing().when(notificacionRepository).deleteById(1L);
        notificacionService.eliminar(1L);
        verify(notificacionRepository, times(1)).deleteById(1L);
    }
}
