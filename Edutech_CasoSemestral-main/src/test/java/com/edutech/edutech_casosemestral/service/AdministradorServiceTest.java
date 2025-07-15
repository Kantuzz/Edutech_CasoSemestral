package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Administrador;
import com.edutech.edutech_casosemestral.repository.AdministradorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdministradorServiceTest {

    @Mock
    private AdministradorRepository administradorRepository;

    @InjectMocks
    private AdministradorService administradorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {
        Administrador admin1 = new Administrador();
        admin1.setNombre("Admin Uno");

        Administrador admin2 = new Administrador();
        admin2.setNombre("Admin Dos");

        when(administradorRepository.findAll()).thenReturn(Arrays.asList(admin1, admin2));

        List<Administrador> resultado = administradorService.listar();

        assertEquals(2, resultado.size());
        verify(administradorRepository, times(1)).findAll();
    }

    @Test
    void testGuardar() {
        Administrador admin = new Administrador();
        admin.setNombre("Nuevo Admin");

        when(administradorRepository.save(admin)).thenReturn(admin);

        Administrador guardado = administradorService.guardar(admin);

        assertNotNull(guardado);
        assertEquals("Nuevo Admin", guardado.getNombre());
        verify(administradorRepository, times(1)).save(admin);
    }

    @Test
    void testActualizar() {
        Administrador existente = new Administrador();
        existente.setId(1L);
        existente.setNombre("Original");

        Administrador actualizado = new Administrador();
        actualizado.setNombre("Actualizado");

        when(administradorRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(administradorRepository.save(any(Administrador.class))).thenReturn(actualizado);

        Administrador resultado = administradorService.actualizar(1L, actualizado);

        assertEquals("Actualizado", resultado.getNombre());
        verify(administradorRepository).findById(1L);
        verify(administradorRepository).save(any(Administrador.class));
    }

    @Test
    void testEliminar() {
        doNothing().when(administradorRepository).deleteById(1L);

        administradorService.eliminar(1L);

        verify(administradorRepository, times(1)).deleteById(1L);
    }
}
