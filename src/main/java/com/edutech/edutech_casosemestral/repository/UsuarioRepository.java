package com.edutech.edutech_casosemestral.repository;

import com.edutech.edutech_casosemestral.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByRut(String rut);
}
