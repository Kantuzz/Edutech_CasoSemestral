package com.edutech.edutech_casosemestral.assembler;

import com.edutech.edutech_casosemestral.controller.InstructorController;
import com.edutech.edutech_casosemestral.model.Instructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class InstructorModelAssembler implements RepresentationModelAssembler<Instructor, EntityModel<Instructor>> {

    @Override
    public EntityModel<Instructor> toModel(Instructor instructor) {
        return EntityModel.of(
                instructor,
                linkTo(methodOn(InstructorController.class).obtenerPorId(instructor.getId())).withSelfRel(),
                linkTo(methodOn(InstructorController.class).listar()).withRel("instructores")
        );
    }
}
