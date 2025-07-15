package com.edutech.edutech_casosemestral.assembler;

import com.edutech.edutech_casosemestral.controller.ContenidoController;
import com.edutech.edutech_casosemestral.model.Contenido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ContenidoModelAssembler implements RepresentationModelAssembler<Contenido, EntityModel<Contenido>> {

    @Override
    public EntityModel<Contenido> toModel(Contenido contenido) {
        return EntityModel.of(
                contenido,
                linkTo(methodOn(ContenidoController.class).obtenerPorId(contenido.getId())).withSelfRel(),
                linkTo(methodOn(ContenidoController.class).listarConLinks()).withRel("contenidos")
        );
    }
}
