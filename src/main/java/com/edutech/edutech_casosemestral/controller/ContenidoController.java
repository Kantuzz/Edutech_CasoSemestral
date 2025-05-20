package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Contenido;
import com.edutech.edutech_casosemestral.service.ContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contenidos")
public class ContenidoController {

    @Autowired
    private ContenidoService service;

    @GetMapping
    public List<Contenido> listar() {
        return service.listar();
    }

    @PostMapping
    public Contenido guardar(@RequestBody Contenido contenido) {
        return service.guardar(contenido);
    }

    @PutMapping("/{id}")
    public Contenido actualizar(@PathVariable Long id, @RequestBody Contenido contenido) {
        return service.actualizar(id, contenido);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
