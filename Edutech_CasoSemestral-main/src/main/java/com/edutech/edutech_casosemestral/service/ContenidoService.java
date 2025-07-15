package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Contenido;
import com.edutech.edutech_casosemestral.repository.ContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContenidoService {

    @Autowired
    private ContenidoRepository repository;

    public List<Contenido> listar() {
        return repository.findAll();
    }

    public Contenido guardar(Contenido contenido) {
        return repository.save(contenido);
    }

    public Contenido actualizar(Long id, Contenido contenido) {
        contenido.setId(id);
        return repository.save(contenido);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Contenido obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contenido no encontrado con id: " + id));
    }

}
