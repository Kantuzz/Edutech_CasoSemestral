package com.edutech.edutech_casosemestral.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private Double notaMaxima;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}
