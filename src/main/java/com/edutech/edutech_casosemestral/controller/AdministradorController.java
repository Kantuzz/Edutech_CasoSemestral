package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Administrador;
import com.edutech.edutech_casosemestral.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService service;

    @GetMapping
    public List<Administrador> listar() {
        return service.listar();
    }

    @PostMapping
    public Administrador guardar(@RequestBody Administrador admin) {
        return service.guardar(admin);
    }

    @PutMapping("/{id}")
    public Administrador actualizar(@PathVariable Long id, @RequestBody Administrador admin) {
        return service.actualizar(id, admin);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
