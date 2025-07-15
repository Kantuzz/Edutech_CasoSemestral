package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.assembler.SoporteModelAssembler;
import com.edutech.edutech_casosemestral.model.Soporte;
import com.edutech.edutech_casosemestral.service.SoporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;

import java.util.List;

@RestController
@RequestMapping("/api/v1/soportes")
@Tag(name = "Soporte", description = "Operaciones relacionadas con el Soporte")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @Autowired
    private SoporteModelAssembler assembler;

    @Operation(summary = "Listar todos los tickets de soporte con enlaces HATEOAS", description = "Devuelve una lista de soportes con enlaces HATEOAS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de soportes obtenido correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno al obtener soportes")
    })
    @GetMapping("/hateoas")
    public CollectionModel<EntityModel<Soporte>> listarConLinks() {
        List<EntityModel<Soporte>> soportes = soporteService.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                soportes,
                linkTo(methodOn(SoporteController.class).listarConLinks()).withSelfRel()
        );
    }

    @Operation(summary = "Obtener soporte por ID", description = "Devuelve un soporte por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Soporte encontrado"),
            @ApiResponse(responseCode = "404", description = "Soporte no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<Soporte> obtenerPorId(
            @Parameter(description = "ID del soporte a obtener") @PathVariable Long id) {
        Soporte soporte = soporteService.obtenerPorId(id);
        return assembler.toModel(soporte);
    }

    @Operation(summary = "Guardar un nuevo soporte", description = "Crea y guarda un nuevo soporte en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Soporte creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos para el soporte")
    })
    @PostMapping
    public Soporte guardar(@RequestBody Soporte soporte) {
        return soporteService.guardar(soporte);
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
        return soporteService.actualizar(id, soporte);
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
        soporteService.eliminar(id);
    }
}
