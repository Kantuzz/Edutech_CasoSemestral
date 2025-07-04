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
        if (curso.getInstructor() == null || curso.getInstructor().getId() == null) {
            throw new IllegalArgumentException("El campo instructor.id no puede ser null.");
        }

        Instructor instructor = instructorRepository.findById(curso.getInstructor().getId())
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));

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

    public Curso obtenerPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " + id));
    }

}
