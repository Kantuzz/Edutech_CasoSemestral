package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Soporte;
import com.edutech.edutech_casosemestral.repository.SoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoporteService {

    @Autowired
    private SoporteRepository repository;

    public List<Soporte> listar() {
        return repository.findAll();
    }

    public Soporte guardar(Soporte soporte) {
        return repository.save(soporte);
    }

    public Soporte actualizar(Long id, Soporte soporte) {
        soporte.setId(id);
        return repository.save(soporte);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Soporte obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soporte no encontrado con id: " + id));
    }

}
