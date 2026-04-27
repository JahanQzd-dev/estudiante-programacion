package com.ejercicios.estudianteProgramacion.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemaDTO {
    private Long idTema;
    private String nombre;
    private String descripcion;
    private Long idCurso;
}
