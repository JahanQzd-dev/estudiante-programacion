package com.ejercicios.estudianteProgramacion.dto;


import com.ejercicios.estudianteProgramacion.model.Tema;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoDTO {
    private Long idCurso;
    private String nombre;
    private String modalidad;
    private LocalDate fechaFinalizacion;
    private List<TemaDTO> listaTemas;
}
