package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Soporte;
import com.edutech.edutech_casosemestral.service.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/soportes")
public class SoporteController {

    @Autowired
    private SoporteService service;

    @GetMapping
    public List<Soporte> listar() {
        return service.listar();
    }

    @PostMapping
    public Soporte guardar(@RequestBody Soporte soporte) {
        return service.guardar(soporte);
    }

    @PutMapping("/{id}")
    public Soporte actualizar(@PathVariable Long id, @RequestBody Soporte soporte) {
        return service.actualizar(id, soporte);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
