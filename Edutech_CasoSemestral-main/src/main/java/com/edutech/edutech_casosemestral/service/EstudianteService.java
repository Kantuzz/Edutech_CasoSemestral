package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Curso;
import com.edutech.edutech_casosemestral.model.Estudiante;
import com.edutech.edutech_casosemestral.repository.CursoRepository;
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



    @Autowired
    private CursoRepository cursoRepository;

    public Estudiante asignarCursos(Long estudianteId, List<Long> cursoIds) {
        Estudiante estudiante = repository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        List<Curso> cursos = cursoRepository.findAllById(cursoIds);
        estudiante.setCursos(cursos);

        return repository.save(estudiante);
    }
    public Estudiante obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + id));
    }


}
