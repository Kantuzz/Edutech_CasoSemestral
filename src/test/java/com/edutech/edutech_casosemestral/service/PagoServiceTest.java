package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Pago;
import com.edutech.edutech_casosemestral.repository.PagoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PagoServiceTest {

    @InjectMocks
    private PagoService pagoService;

    @Mock
    private PagoRepository pagoRepository;

    private Pago pago;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pago = new Pago();
        pago.setMonto(15000.0);
    }

    @Test
    public void testGuardar() {
        when(pagoRepository.save(pago)).thenReturn(pago);
        Pago result = pagoService.guardar(pago);
        assertEquals(15000.0, result.getMonto());
    }

    @Test
    public void testListar() {
        when(pagoRepository.findAll()).thenReturn(List.of(pago));
        List<Pago> lista = pagoService.listar();
        assertEquals(1, lista.size());
    }

    @Test
    public void testActualizar() {
        Pago nuevo = new Pago();
        nuevo.setMonto(25000.0);

        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));
        when(pagoRepository.save(any())).thenReturn(nuevo);

        Pago result = pagoService.actualizar(1L, nuevo);
        assertEquals(25000.0, result.getMonto());
    }

    @Test
    public void testEliminar() {
        doNothing().when(pagoRepository).deleteById(1L);
        pagoService.eliminar(1L);
        verify(pagoRepository, times(1)).deleteById(1L);
    }
}
