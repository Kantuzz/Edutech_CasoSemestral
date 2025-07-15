package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Notificacion;
import com.edutech.edutech_casosemestral.service.NotificacionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NotificacionControllerTest {

    @Mock
    private NotificacionService notificacionService;

    @InjectMocks
    private NotificacionController notificacionController;

    public NotificacionControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardar() {
        Notificacion noti = new Notificacion();
        noti.setMensaje("Nuevo mensaje");

        when(notificacionService.guardar(noti)).thenReturn(noti);

        Notificacion result = notificacionController.guardar(noti);
        assertEquals("Nuevo mensaje", result.getMensaje());
    }

    @Test
    public void testListar() {
        when(notificacionService.listar()).thenReturn(List.of(new Notificacion()));
        List<Notificacion> lista = notificacionController.listar();
        assertEquals(1, lista.size());
    }

    @Test
    public void testActualizar() {
        Notificacion noti = new Notificacion();
        noti.setMensaje("Mensaje actualizado");

        when(notificacionService.actualizar(1L, noti)).thenReturn(noti);

        Notificacion result = notificacionController.actualizar(1L, noti);
        assertEquals("Mensaje actualizado", result.getMensaje());
    }

    @Test
    public void testEliminar() {
        doNothing().when(notificacionService).eliminar(1L);
        notificacionController.eliminar(1L);
        verify(notificacionService, times(1)).eliminar(1L);
    }
}
