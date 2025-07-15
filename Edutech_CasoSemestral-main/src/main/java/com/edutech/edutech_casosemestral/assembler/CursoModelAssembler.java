package com.edutech.edutech_casosemestral.assembler;

import com.edutech.edutech_casosemestral.controller.CursoController;
import com.edutech.edutech_casosemestral.model.Curso;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CursoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>> {

    @Override
    public EntityModel<Curso> toModel(Curso curso) {
        return EntityModel.of(
                curso,
                linkTo(methodOn(CursoController.class).obtenerPorId(curso.getId())).withSelfRel(),
                linkTo(methodOn(CursoController.class).listarConLinks()).withRel("cursos")
        );
    }
}