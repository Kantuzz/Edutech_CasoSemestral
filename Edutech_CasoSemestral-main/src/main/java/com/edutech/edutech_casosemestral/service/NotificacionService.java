package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Notificacion;
import com.edutech.edutech_casosemestral.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> listar() {
        return notificacionRepository.findAll();
    }

    public Notificacion guardar(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public Notificacion actualizar(Long id, Notificacion notificacion) {
        notificacion.setId(id);
        return notificacionRepository.save(notificacion);
    }

    public void eliminar(Long id) {
        notificacionRepository.deleteById(id);
    }

    public Notificacion obtenerPorId(Long id) {
        return notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada con id: " + id));
    }

}
