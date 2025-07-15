package com.edutech.edutech_casosemestral.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double monto;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;
}