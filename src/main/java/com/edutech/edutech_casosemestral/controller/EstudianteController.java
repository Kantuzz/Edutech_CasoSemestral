package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Estudiante;
import com.edutech.edutech_casosemestral.service.EstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estudiantes")
@Tag(name = "Estudiantes", description = "Operaciones relacionadas con los estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService service;

    @Operation(summary = "Listar todos los Estudiantes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno al obetener Estudiantes")
    })
    @GetMapping

    public List<Estudiante> listar() {
        return service.listar();
    }

    @Operation(summary = "Guardar un nuevo estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante guardado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos Invalidos")
    })

    @PostMapping
    public Estudiante guardar(@RequestBody Estudiante estudiante) {
        return service.guardar(estudiante);
    }

    @Operation(summary = "Actualizar un estudiante existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })

    @PutMapping("/{id}")
    public Estudiante actualizar(
            @Parameter(description = "ID del estudiante a actualizar", required = true)
            @PathVariable Long id, @RequestBody Estudiante estudiante) {
        return service.actualizar(id, estudiante);
    }

    @Operation(summary = "Eliminar un estudiante por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })

    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID del estudiante a eliminar", required = true)
            @PathVariable Long id) {
        service.eliminar(id);
    }

    @Operation(summary = "Asignar cursos a un estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cursos asignados correctamente"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })

    @PutMapping("/{id}/cursos")
    public Estudiante asignarCursos(
            @Parameter(description = "ID del estudiante", required = true)
            @PathVariable Long id, @RequestBody List<Long> cursoIds) {
        return service.asignarCursos(id, cursoIds);
    }

}
