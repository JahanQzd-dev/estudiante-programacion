package com.ejercicios.estudianteProgramacion.model;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Tema {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idTema;
    private String nombre;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "idCurso")
    private Curso curso;

}
