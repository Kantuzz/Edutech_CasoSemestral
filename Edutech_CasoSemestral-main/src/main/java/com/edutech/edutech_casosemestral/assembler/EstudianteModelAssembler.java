package com.edutech.edutech_casosemestral.assembler;

import com.edutech.edutech_casosemestral.controller.EstudianteController;
import com.edutech.edutech_casosemestral.model.Estudiante;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EstudianteModelAssembler implements RepresentationModelAssembler<Estudiante, EntityModel<Estudiante>> {

    @Override
    public EntityModel<Estudiante> toModel(Estudiante estudiante) {
        return EntityModel.of(
                estudiante,
                linkTo(methodOn(EstudianteController.class).obtenerPorId(estudiante.getId())).withSelfRel(),
                linkTo(methodOn(EstudianteController.class).listarConLinks()).withRel("estudiantes")
        );
    }
}
