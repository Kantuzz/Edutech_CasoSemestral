package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Contenido;
import com.edutech.edutech_casosemestral.service.ContenidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.edutech.edutech_casosemestral.assembler.ContenidoModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contenidos")
@Tag(name = "Contenidos", description = "Operaciones relacionadas con contenidos")
public class ContenidoController {

    @Autowired
    private ContenidoService service;

    @Autowired
    private ContenidoModelAssembler assembler;

    @Operation(summary = "Listar todos los con enlaces HATEOAS", description = "Retorna una lista de todos los contenidos disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/hateoas")
    public CollectionModel<EntityModel<Contenido>> listarConLinks() {
        List<EntityModel<Contenido>> contenidos = service.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(
                contenidos,
                linkTo(methodOn(ContenidoController.class).listarConLinks()).withSelfRel()
        );
    }

    @Operation(summary = "Obtener contenido por ID", description = "Devuelve un contenido específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contenido encontrado"),
            @ApiResponse(responseCode = "404", description = "Contenido no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<Contenido> obtenerPorId(@PathVariable Long id) {
        Contenido contenido = service.obtenerPorId(id);
        return assembler.toModel(contenido);
    }
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
