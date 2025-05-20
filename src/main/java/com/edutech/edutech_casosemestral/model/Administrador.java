package com.edutech.edutech_casosemestral.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Administrador extends Usuario {


    public void gestionarUsuarios() {}

    public void configurarPermisos() {}
}
