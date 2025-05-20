package com.edutech.edutech_casosemestral.service;

import com.edutech.edutech_casosemestral.model.Usuario;
import com.edutech.edutech_casosemestral.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario guardar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario actualizar(Long id, Usuario usuario) {
        usuario.setId(id);
        return repository.save(usuario);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Optional<Usuario> buscarPorRut(String rut) {
        return repository.findByRut(rut);
    }
}
