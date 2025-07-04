package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Evaluacion;
import com.edutech.edutech_casosemestral.service.EvaluacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.edutech.edutech_casosemestral.assembler.EvaluacionModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@Tag(name = "Evaluaciones", description = "Operaciones relacionadas con las evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    private EvaluacionModelAssembler assembler;


    @Operation(summary = "Listar todas las evaluaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno al obtener evaluaciones")
    })
    @GetMapping
    public CollectionModel<EntityModel<Evaluacion>> listar() {
        List<EntityModel<Evaluacion>> evaluaciones = evaluacionService.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(
                evaluaciones,
                linkTo(methodOn(EvaluacionController.class).listar()).withSelfRel()
        );
    }

    @Operation(summary = "Obtener evaluación por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluación encontrada"),
            @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    })
    @GetMapping("/{id}")
    public EntityModel<Evaluacion> obtenerPorId(
            @Parameter(description = "ID de la evaluación a obtener", required = true)
            @PathVariable Long id) {
        Evaluacion evaluacion = evaluacionService.obtenerPorId(id);
        return assembler.toModel(evaluacion);
    }


    @PostMapping
    public Evaluacion guardar(@RequestBody Evaluacion evaluacion) {
        return evaluacionService.guardar(evaluacion);
    }

    @Operation(summary = "Actualizar una evaluacion existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluacion actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Evaluacion no encontrada")
    })

    @PutMapping("/{id}")
    public Evaluacion actualizar(
            @Parameter(description = "ID de la evaluacion a actualizar", required = true)
            @PathVariable Long id, @RequestBody Evaluacion evaluacion) {
        return evaluacionService.actualizar(id, evaluacion);
    }

    @Operation(summary = "Eliminar una evaluacion por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluacion eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Evaluacion no encontrada")
    })

    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID de la evaluacion a eliminar", required = true)
            @PathVariable Long id) {
        evaluacionService.eliminar(id);
    }
}
