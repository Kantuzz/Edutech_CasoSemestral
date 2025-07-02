package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Pago;
import com.edutech.edutech_casosemestral.service.PagoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PagoControllerTest {

    @Mock
    private PagoService pagoService;

    @InjectMocks
    private PagoController pagoController;

    public PagoControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardar() {
        Pago pago = new Pago();
        pago.setMonto(10000.0);

        when(pagoService.guardar(pago)).thenReturn(pago);

        Pago result = pagoController.guardar(pago);
        assertEquals(10000.0, result.getMonto());
    }

    @Test
    public void testListar() {
        when(pagoService.listar()).thenReturn(List.of(new Pago()));
        List<Pago> lista = pagoController.listar();
        assertEquals(1, lista.size());
    }

    @Test
    public void testActualizar() {
        Pago pago = new Pago();
        pago.setMonto(20000.0);

        when(pagoService.actualizar(1L, pago)).thenReturn(pago);

        Pago result = pagoController.actualizar(1L, pago);
        assertEquals(20000.0, result.getMonto());
    }

    @Test
    public void testEliminar() {
        doNothing().when(pagoService).eliminar(1L);
        pagoController.eliminar(1L);
        verify(pagoService, times(1)).eliminar(1L);
    }
}
