package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Evaluacion;
import com.edutech.edutech_casosemestral.service.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping
    public List<Evaluacion> listar() {
        return evaluacionService.listar();
    }

    @PostMapping
    public Evaluacion guardar(@RequestBody Evaluacion evaluacion) {
        return evaluacionService.guardar(evaluacion);
    }

    @PutMapping("/{id}")
    public Evaluacion actualizar(@PathVariable Long id, @RequestBody Evaluacion evaluacion) {
        return evaluacionService.actualizar(id, evaluacion);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        evaluacionService.eliminar(id);
    }
}
