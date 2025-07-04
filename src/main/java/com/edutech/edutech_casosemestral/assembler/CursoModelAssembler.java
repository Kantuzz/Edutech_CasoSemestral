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
        EntityModel<Curso> model = EntityModel.of(curso);

        if (curso.getId() != null && curso.getId() > 0) {
            model.add(linkTo(methodOn(CursoController.class).getCursoById(curso.getId())).withSelfRel());
        }

        model.add(linkTo(methodOn(CursoController.class).getAllCursos()).withRel("cursos"));

        return model;
    }
}