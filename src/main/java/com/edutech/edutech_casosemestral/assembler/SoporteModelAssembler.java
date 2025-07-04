package com.edutech.edutech_casosemestral.assembler;

import com.edutech.edutech_casosemestral.controller.SoporteController;
import com.edutech.edutech_casosemestral.model.Soporte;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SoporteModelAssembler implements RepresentationModelAssembler<Soporte, EntityModel<Soporte>> {

    @Override
    public EntityModel<Soporte> toModel(Soporte soporte) {
        return EntityModel.of(
                soporte,
                linkTo(methodOn(SoporteController.class).obtenerPorId(soporte.getId())).withSelfRel(),
                linkTo(methodOn(SoporteController.class).listar()).withRel("soportes")
        );
    }
}
