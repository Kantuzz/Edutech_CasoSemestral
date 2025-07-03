package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Soporte;
import com.edutech.edutech_casosemestral.service.SoporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/soportes")
@Tag(name = "Soporte", description = "Operaciones relacionadas con el Soporte")
public class SoporteController {

    @Autowired
    private SoporteService service;

    @Operation(summary = "Listar todos los soportes", description = "Devuelve una lista de todos los soportes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })

    @GetMapping
    public List<Soporte> listar() {
        return service.listar();
    }

    @Operation(summary = "Guardar un nuevo soporte", description = "Crea y guarda un nuevo soporte en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Soporte creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos para el soporte")
    })

    @PostMapping
    public Soporte guardar(@RequestBody Soporte soporte) {
        return service.guardar(soporte);
    }

    @Operation(summary = "Actualizar un soporte", description = "Actualiza un soporte existente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Soporte actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Soporte no encontrado")
    })

    @PutMapping("/{id}")
    public Soporte actualizar(
            @Parameter(description = "ID del soporte a actualizar")
            @PathVariable Long id,
            @RequestBody Soporte soporte) {
        return service.actualizar(id, soporte);
    }

    @Operation(summary = "Eliminar un soporte", description = "Elimina un soporte por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Soporte eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Soporte no encontrado")
    })

    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID del soporte a eliminar")
            @PathVariable Long id) {
        service.eliminar(id);
    }
}
