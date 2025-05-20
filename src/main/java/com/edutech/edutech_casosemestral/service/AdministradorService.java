package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Administrador;
import com.edutech.edutech_casosemestral.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository repository;

    public List<Administrador> listar() {
        return repository.findAll();
    }

    public Administrador guardar(Administrador administrador) {
        return repository.save(administrador);
    }

    public Administrador actualizar(Long id, Administrador administrador) {
        administrador.setId(id);
        return repository.save(administrador);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
