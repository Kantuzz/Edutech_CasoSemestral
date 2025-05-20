package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Instructor;
import com.edutech.edutech_casosemestral.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructores")
public class InstructorController {

    @Autowired
    private InstructorService service;

    @GetMapping
    public List<Instructor> listar() {
        return service.listar();
    }

    @PostMapping
    public Instructor guardar(@RequestBody Instructor instructor) {
        return service.guardar(instructor);
    }

    @PutMapping("/{id}")
    public Instructor actualizar(@PathVariable Long id, @RequestBody Instructor instructor) {
        return service.actualizar(id, instructor);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
