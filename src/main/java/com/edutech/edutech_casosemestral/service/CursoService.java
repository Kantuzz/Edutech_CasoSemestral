package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Curso;
import com.edutech.edutech_casosemestral.model.Instructor;
import com.edutech.edutech_casosemestral.repository.CursoRepository;
import com.edutech.edutech_casosemestral.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    public Curso guardar(Curso curso) {
        Long instructorId = curso.getInstructor().getId();

        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado con ID: " + instructorId));

        curso.setInstructor(instructor);
        return cursoRepository.save(curso);
    }

    public Curso actualizar(Long id, Curso curso) {
        curso.setId(id);
        return cursoRepository.save(curso);
    }

    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }
}
