package com.edutech.edutech_casosemestral.assembler;

import com.edutech.edutech_casosemestral.controller.AdministradorController;
import com.edutech.edutech_casosemestral.model.Administrador;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AdministradorModelAssembler implements RepresentationModelAssembler<Administrador, EntityModel<Administrador>> {

    @Override
    public EntityModel<Administrador> toModel(Administrador admin) {
        return EntityModel.of(
                admin,
                linkTo(methodOn(AdministradorController.class).obtenerPorId(admin.getId())).withSelfRel(),
                linkTo(methodOn(AdministradorController.class).listar()).withRel("administradores")
        );
    }
}
