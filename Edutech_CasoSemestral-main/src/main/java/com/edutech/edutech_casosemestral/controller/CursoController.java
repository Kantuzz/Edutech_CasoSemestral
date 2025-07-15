package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.assembler.CursoModelAssembler;
import com.edutech.edutech_casosemestral.model.Curso;
import com.edutech.edutech_casosemestral.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/cursos")
@Tag(name = "Cursos", description = "Operaciones relacionadas con los cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoModelAssembler assembler;

    @Operation(summary = "Listar todos los cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de cursos obtenido correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno al obtener cursos")
    })
    @GetMapping
    public List<Curso> listar() {
        return cursoService.listar();
    }

    @Operation(summary = "Listar cursos con enlaces HATEOAS")
    @ApiResponse(responseCode = "200", description = "Cursos con enlaces HATEOAS")
    @GetMapping("/hateoas")
    public CollectionModel<EntityModel<Curso>> listarConLinks() {
        List<Curso> cursos = cursoService.listar();

        List<EntityModel<Curso>> cursoModels = cursos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cursoModels,
                linkTo(methodOn(CursoController.class).listarConLinks()).withSelfRel());
    }

    @Operation(summary = "Guardar un nuevo curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso guardado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    @PostMapping
    public Curso guardar(@RequestBody Curso curso) {
        return cursoService.guardar(curso);
    }

    @Operation(summary = "Actualizar un curso existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<Curso> obtenerPorId(@PathVariable Long id) {
        Curso curso = cursoService.obtenerPorId(id);
        return assembler.toModel(curso);
    }

    @Operation(summary = "Eliminar un curso por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID del curso a eliminar", required = true)
            @PathVariable Long id) {
        cursoService.eliminar(id);
    }

    // Métodos utilizados internamente por el assembler

    @Operation(hidden = true)
    @GetMapping("/hateoas/{id}")
    public Curso getCursoById(@PathVariable Long id) {
        return cursoService.listar().stream()
                .filter(curso -> curso.getId().equals(id))
                .findFirst()
                .orElseThrow(); // Puedes reemplazar por tu excepción personalizada
    }

    @Operation(hidden = true)
    @GetMapping("/hateoas/all")
    public List<Curso> getAllCursos() {
        return cursoService.listar();
    }
}
