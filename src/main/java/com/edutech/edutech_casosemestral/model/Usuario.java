package com.edutech.edutech_casosemestral.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 10, nullable = false)
    private String rut;

    private String nombre;
    private String correo;

    private String rol;


    public void setId(Long id) {
    }
}
