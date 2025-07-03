package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Notificacion;
import com.edutech.edutech_casosemestral.service.NotificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
@Tag(name = "Notificaciones", description = "Operaciones relacionadas con las Notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @Operation(summary = "Listar todas las notificaciones", description = "Devuelve una lista de todas las notificaciones registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente")
    })

    @GetMapping
    public List<Notificacion> listar() {
        return notificacionService.listar();
    }

    @Operation(summary = "Guardar nueva notificacion", description = "Crea una nueva notificacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notificacion creada"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })

    @PostMapping
    public Notificacion guardar(@RequestBody Notificacion notificacion) {
        return notificacionService.guardar(notificacion);
    }

    @Operation(summary = "Actualizar notificacion", description = "Actualiza una notificacion existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificacion actualizada"),
            @ApiResponse(responseCode = "404", description = "Notificacion no encontrada")
    })

    @PutMapping("/{id}")
    public Notificacion actualizar(
            @Parameter(description = "ID de la notificacion a actualizar")
            @PathVariable Long id,
            @RequestBody Notificacion notificacion) {
        return notificacionService.actualizar(id, notificacion);
    }

    @Operation(summary = "Eliminar notificacion", description = "Elimina una notificacion por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Notificacion eliminada"),
            @ApiResponse(responseCode = "404", description = "Notificacion no encontrada")
    })

    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID de la notificacion a eliminar")
            @PathVariable Long id) {
        notificacionService.eliminar(id);
    }
}
