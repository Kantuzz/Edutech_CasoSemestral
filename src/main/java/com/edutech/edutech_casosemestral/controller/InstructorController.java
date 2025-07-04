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
import com.edutech.edutech_casosemestral.assembler.InstructorModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


import java.util.List;

@RestController
@RequestMapping("/api/v1/instructores")
@Tag(name = "Instructores", description = "Operaciones relacionadas con los Instructores")
public class InstructorController {

    @Autowired
    private InstructorService service;

    @Autowired
    private InstructorModelAssembler assembler;


    @Operation(summary = "Listar todos los instructores", description = "Obtiene una lista con todos los instructores registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping
    public CollectionModel<EntityModel<Instructor>> listar() {
        List<EntityModel<Instructor>> instructores = service.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(
                instructores,
                linkTo(methodOn(InstructorController.class).listar()).withSelfRel()
        );
    }

    @Operation(summary = "Obtener instructor por ID", description = "Obtiene un instructor por su ID con enlaces HATEOAS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor encontrado"),
            @ApiResponse(responseCode = "404", description = "Instructor no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<Instructor> obtenerPorId(
            @Parameter(description = "ID del instructor a obtener") @PathVariable Long id) {
        Instructor instructor = service.obtenerPorId(id);
        return assembler.toModel(instructor);
    }


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