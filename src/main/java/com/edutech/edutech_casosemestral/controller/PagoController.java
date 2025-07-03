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

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
@Tag(name = "Pagos", description = "Operaciones relacionadas con los Pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Operation(summary = "Listar todos los pagos", description = "Devuelve una lista de todos los pagos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })

    @GetMapping
    public List<Pago> listar() {
        return pagoService.listar();
    }

    @Operation(summary = "Guardar un nuevo pago", description = "Crea y guarda un nuevo pago en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pago creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del pago invalidos")
    })

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
