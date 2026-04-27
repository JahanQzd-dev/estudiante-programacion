package com.ejercicios.estudianteProgramacion.service;

import com.ejercicios.estudianteProgramacion.dto.TemaDTO;

import java.util.List;

public interface ITemaService {

    // Métodos abstractos
    List<TemaDTO> traerTemas();
    TemaDTO traerTema(Long id);
    TemaDTO crearTema(TemaDTO temaDto);
    TemaDTO actualizarTema(Long id, TemaDTO temaDto);
    void eliminarTema(Long id);

}
