package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Administrador;
import com.edutech.edutech_casosemestral.service.AdministradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/administradores")
@Tag(name = "Administradores", description = "Operaciones relacionadas con la Administracion")
public class AdministradorController {

    @Autowired
    private AdministradorService service;

    @Operation(summary = "Listar todos los administradores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno al obtener administradores")
    })

    @GetMapping
    public List<Administrador> listar() {
        return service.listar();
    }

    @Operation(summary = "Guardar un nuevo administrador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador guardado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })

    @PostMapping
    public Administrador guardar(@RequestBody Administrador admin) {
        return service.guardar(admin);
    }

    @Operation(summary = "Actualizar un administrador existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })

    @PutMapping("/{id}")
    public Administrador actualizar(
            @Parameter(description = "ID del administrado a actualizar", required = true)
            @PathVariable Long id, @RequestBody Administrador admin) {
        return service.actualizar(id, admin);
    }

    @Operation(summary = "Eliminar un administrador por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrado eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })

    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID del administrador a eliminar", required = true)
            @PathVariable Long id) {
        service.eliminar(id);
    }
}
