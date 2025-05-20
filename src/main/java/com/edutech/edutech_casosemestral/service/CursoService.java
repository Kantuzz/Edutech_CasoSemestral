package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Curso;
import com.edutech.edutech_casosemestral.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    public List<Curso> listar() {
        return repository.findAll();
    }

    public Curso guardar(Curso curso) {
        return repository.save(curso);
    }

    public Curso actualizar(Long id, Curso curso) {
        curso.setId(id);
        return repository.save(curso);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
