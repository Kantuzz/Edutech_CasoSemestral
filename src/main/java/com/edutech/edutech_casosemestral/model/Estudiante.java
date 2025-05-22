package com.edutech.edutech_casosemestral.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Estudiante extends Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "CURSO_ESTUDIANTE",
            joinColumns = @JoinColumn(name = "ESTUDIANTE_ID"),
            inverseJoinColumns = @JoinColumn(name = "CURSO_ID")
    )
    private List<Curso> cursos;


    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }



    //public void inscribirseEnCurso() {}

    //public void realizarPago() {}
}
