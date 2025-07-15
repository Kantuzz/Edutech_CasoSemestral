package com.edutech.edutech_casosemestral.assembler;

import com.edutech.edutech_casosemestral.controller.NotificacionController;
import com.edutech.edutech_casosemestral.model.Notificacion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class NotificacionModelAssembler implements RepresentationModelAssembler<Notificacion, EntityModel<Notificacion>> {

    @Override
    public EntityModel<Notificacion> toModel(Notificacion notificacion) {
        return EntityModel.of(
                notificacion,
                linkTo(methodOn(NotificacionController.class).obtenerPorId(notificacion.getId())).withSelfRel(),
                linkTo(methodOn(NotificacionController.class).listarConLinks()).withRel("notificaciones")
        );
    }
}
