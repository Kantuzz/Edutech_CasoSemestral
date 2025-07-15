package com.edutech.edutech_casosemestral.model;

import jakarta.persistence.*;
import lombok.Data;

//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@MappedSuperclass
@Data
public class Usuario {
    private String rut;
    private String nombre;
    private String correo;
    private String rol;
}
