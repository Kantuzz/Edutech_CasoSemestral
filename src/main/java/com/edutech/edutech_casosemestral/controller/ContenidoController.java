package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Contenido;
import com.edutech.edutech_casosemestral.service.ContenidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contenidos")
@Tag(name = "Contenidos", description = "Operaciones relacionadas con contenidos")
public class ContenidoController {

    @Autowired
    private ContenidoService service;

    @Operation(summary = "Listar todos los contenidos", description = "Retorna una lista de todos los contenidos disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @GetMapping
    public List<Contenido> listar() {
        return service.listar();
    }

    @Operation(summary = "Guardar nuevo contenido", description = "Crea un nuevo contenido en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contenido creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @PostMapping
    public Contenido guardar(@RequestBody Contenido contenido) {
        return service.guardar(contenido);
    }

    @Operation(summary = "Actualizar un contenido", description = "Actualiza un cotnenido existente usando su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contenido actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Contenido no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @PutMapping("/{id}")
    public Contenido actualizar(@PathVariable Long id, @RequestBody Contenido contenido) {
        return service.actualizar(id, contenido);
    }

    @Operation(summary = "Eliminar un contenido", description = "Elimina un contenido existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contenido eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Contenido no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
