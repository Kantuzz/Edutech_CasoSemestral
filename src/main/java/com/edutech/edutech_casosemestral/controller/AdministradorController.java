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
import com.edutech.edutech_casosemestral.assembler.AdministradorModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


import java.util.List;

@RestController
@RequestMapping("/api/v1/administradores")
@Tag(name = "Administradores", description = "Operaciones relacionadas con la Administracion")
public class AdministradorController {

    @Autowired
    private AdministradorService service;

    @Autowired
    private AdministradorModelAssembler assembler;


    @Operation(summary = "Listar todos los administradores con HATEOAS", description = "Devuelve una lista de administradores con enlaces HATEOAS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno al obtener administradores")
    })
    @GetMapping("/hateoas")
    public CollectionModel<EntityModel<Administrador>> listarConLinks() {
        List<EntityModel<Administrador>> administradores = service.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(
                administradores,
                linkTo(methodOn(AdministradorController.class).listarConLinks()).withSelfRel()
        );
    }

    @Operation(summary = "Obtener un administrador por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador encontrado"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<Administrador> obtenerPorId(
            @Parameter(description = "ID del administrador a obtener", required = true)
            @PathVariable Long id) {
        Administrador admin = service.obtenerPorId(id);
        return assembler.toModel(admin);
    }

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
