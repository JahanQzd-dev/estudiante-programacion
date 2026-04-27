package com.ejercicios.estudianteProgramacion.service;

import com.ejercicios.estudianteProgramacion.dto.CursoDTO;
import com.ejercicios.estudianteProgramacion.dto.TemaDTO;

import java.util.List;

public interface ICursoService {

    // Métodos abstractos
    List<CursoDTO> traerCursos();
    CursoDTO traerCurso(Long id);
    CursoDTO crearCurso(CursoDTO cursoDto);
    CursoDTO actualizarCurso(Long id, CursoDTO cursoDto);
    void eliminarCurso(Long id);
    List<TemaDTO> traerTemasPorCurso(Long id);
    List<CursoDTO> traerCursosPorNombre(String nombre);
}
