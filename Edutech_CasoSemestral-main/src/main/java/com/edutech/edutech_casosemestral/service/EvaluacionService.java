package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Curso;
import com.edutech.edutech_casosemestral.model.Evaluacion;
import com.edutech.edutech_casosemestral.repository.CursoRepository;
import com.edutech.edutech_casosemestral.repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<Evaluacion> listar() {
        return evaluacionRepository.findAll();
    }

    public Evaluacion guardar(Evaluacion evaluacion) {
        Curso curso = cursoRepository.findById(evaluacion.getCurso().getId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        evaluacion.setCurso(curso);
        return evaluacionRepository.save(evaluacion);
    }

    public Evaluacion actualizar(Long id, Evaluacion evaluacion) {
        evaluacion.setId(id);
        return evaluacionRepository.save(evaluacion);
    }

    public void eliminar(Long id) {
        evaluacionRepository.deleteById(id);
    }

    public Evaluacion obtenerPorId(Long id) {
        return evaluacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluacion no encontrada con id: " + id));
    }

}
