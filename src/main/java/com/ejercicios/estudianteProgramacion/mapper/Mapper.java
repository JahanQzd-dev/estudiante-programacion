package com.ejercicios.estudianteProgramacion.mapper;

import com.ejercicios.estudianteProgramacion.dto.CursoDTO;
import com.ejercicios.estudianteProgramacion.dto.TemaDTO;
import com.ejercicios.estudianteProgramacion.model.Curso;
import com.ejercicios.estudianteProgramacion.model.Tema;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class Mapper {

    // Mapeo de Curso a CursoDTO
    public static CursoDTO toDTO(Curso c){
        if (c == null) return null;

        var listaTemas = Optional.ofNullable(c.getListaTemas())
                .orElse(Collections.emptyList())
                .stream()
                .map(tema -> TemaDTO.builder()
                        .idTema(tema.getIdTema())
                        .nombre(tema.getNombre())
                        .descripcion(tema.getDescripcion())
                        .idCurso(tema.getCurso().getIdCurso())
                        .build()
                )
                .collect(Collectors.toList());

        return CursoDTO.builder()
                .idCurso(c.getIdCurso())
                .nombre(c.getNombre())
                .modalidad(c.getModalidad())
                .fechaFinalizacion(c.getFechaFinalizacion())
                .listaTemas(listaTemas)
                .build();
    }

    // Mapeo de Tema a TemaDTO
    public static TemaDTO toDTO(Tema t){
        if (t == null) return null;

        return TemaDTO.builder()
                .idTema(t.getIdTema())
                .nombre(t.getNombre())
                .descripcion(t.getDescripcion())
                .idCurso(
                        t.getCurso() != null ? t.getCurso().getIdCurso() : null
                )
                .build();
    }

}
