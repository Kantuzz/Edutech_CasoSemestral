package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Instructor;
import com.edutech.edutech_casosemestral.service.InstructorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructores")
@Tag(name = "Instructores", description = "Operaciones relacionadas con los Instructores")
public class InstructorController {

    @Autowired
    private InstructorService service;

    @Operation(summary = "Listar todos los instructores", description = "Obtiene una lista con todos los instructores registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })

    @GetMapping
    public List<Instructor> listar() {
        return service.listar();
    }

    @Operation(summary = "Guardar un nuevo instructor", description = "Guarda un instructor en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Instructor creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida")
    })

    @PostMapping
    public Instructor guardar(@RequestBody Instructor instructor) {
        return service.guardar(instructor);
    }

    @Operation(summary = "Actualizar un instructor existente", description = "Actualiza los datos de un instructor por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Instructor no encontrado")
    })

    @PutMapping("/{id}")
    public Instructor actualizar(
            @Parameter(description = "ID del instructor a actualizar")
            @PathVariable Long id,
            @RequestBody Instructor instructor) {
        return service.actualizar(id, instructor);
    }

    @Operation(summary = "Eliminar un instructor", description = "Elimina un instructor por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Instructor eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Instructor no encontrado")
    })

    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID del instructor a eliminar")
            @PathVariable Long id) {
        service.eliminar(id);
    }
}