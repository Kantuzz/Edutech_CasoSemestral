package com.edutech.edutech_casosemestral.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private Double precio;

    // Relación Muchos a Uno: un instructor imparte muchos cursos
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    // Relación Muchos a Muchos: estudiantes inscritos en cursos
    @ManyToMany
    @JoinTable(
            name = "curso_estudiante",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id")
    )
    private List<Estudiante> estudiantes = new ArrayList<>();

    //Relación Uno a Muchos: curso tiene múltiples contenidos
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contenido> contenidos = new ArrayList<>();

    //Relación Uno a Muchos: curso incluye múltiples evaluaciones
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evaluacion> evaluaciones = new ArrayList<>();
}
