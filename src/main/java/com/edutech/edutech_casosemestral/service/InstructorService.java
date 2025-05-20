package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Instructor;
import com.edutech.edutech_casosemestral.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository repository;

    public List<Instructor> listar() {
        return repository.findAll();
    }

    public Instructor guardar(Instructor instructor) {
        return repository.save(instructor);
    }

    public Instructor actualizar(Long id, Instructor instructor) {
        instructor.setId(id);
        return repository.save(instructor);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
