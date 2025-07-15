package com.edutech.edutech_casosemestral.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;

    // Relación con Curso
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    // Relación con Instructor
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
}
