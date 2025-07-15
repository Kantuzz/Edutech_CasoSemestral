package com.edutech.edutech_casosemestral.assembler;

import com.edutech.edutech_casosemestral.controller.EvaluacionController;
import com.edutech.edutech_casosemestral.model.Evaluacion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EvaluacionModelAssembler implements RepresentationModelAssembler<Evaluacion, EntityModel<Evaluacion>> {

    @Override
    public EntityModel<Evaluacion> toModel(Evaluacion evaluacion) {
        return EntityModel.of(
                evaluacion,
                linkTo(methodOn(EvaluacionController.class).obtenerPorId(evaluacion.getId())).withSelfRel(),
                linkTo(methodOn(EvaluacionController.class).listarConLinks()).withRel("evaluaciones")
        );
    }
}
