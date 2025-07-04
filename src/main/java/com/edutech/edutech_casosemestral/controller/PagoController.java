package com.edutech.edutech_casosemestral.controller;

import com.edutech.edutech_casosemestral.model.Pago;
import com.edutech.edutech_casosemestral.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.edutech.edutech_casosemestral.assembler.PagoModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.stream.Collectors;


import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
@Tag(name = "Pagos", description = "Operaciones relacionadas con los Pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private PagoModelAssembler assembler;


    @Operation(summary = "Listar todos los pagos", description = "Devuelve una lista de todos los pagos registrados con enlaces HATEOAS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @GetMapping
    public CollectionModel<EntityModel<Pago>> listar() {
        List<EntityModel<Pago>> pagos = pagoService.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(
                pagos,
                linkTo(methodOn(PagoController.class).listar()).withSelfRel()
        );
    }

    @Operation(summary = "Obtener pago por ID", description = "Devuelve un pago por su ID con enlaces HATEOAS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago encontrado"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<Pago> obtenerPorId(
            @Parameter(description = "ID del pago a obtener") @PathVariable Long id) {
        Pago pago = pagoService.obtenerPorId(id);
        return assembler.toModel(pago);
    }


    @PostMapping
    public Pago guardar(@RequestBody Pago pago) {
        return pagoService.guardar(pago);
    }

    @Operation(summary = "Actualizar un pago", description = "Modifica los datos de un pago por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })

    @PutMapping("/{id}")
    public Pago actualizar(
            @Parameter(description = "ID del pago a actualizar")
            @PathVariable Long id,
            @RequestBody Pago pago) {
        return pagoService.actualizar(id, pago);
    }

    @Operation(summary = "Eliminar un pago", description = "Elimina un pago por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pago eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })

    @DeleteMapping("/{id}")
    public void eliminar(
            @Parameter(description = "ID del pago a eliminar")
            @PathVariable Long id) {
        pagoService.eliminar(id);
    }
}
