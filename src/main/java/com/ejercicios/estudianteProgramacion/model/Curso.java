package com.ejercicios.estudianteProgramacion.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Curso {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idCurso;
    private String nombre;
    private String modalidad;
    private LocalDate fechaFinalizacion;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Tema> listaTemas = new ArrayList<>();

}
