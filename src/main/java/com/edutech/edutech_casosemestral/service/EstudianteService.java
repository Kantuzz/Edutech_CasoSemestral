package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Estudiante;
import com.edutech.edutech_casosemestral.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository repository;

    public List<Estudiante> listar() {
        return repository.findAll();
    }

    public Estudiante guardar(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    public Estudiante actualizar(Long id, Estudiante estudiante) {
        estudiante.setId(id);
        return repository.save(estudiante);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
