package com.ejercicios.estudianteProgramacion.repository;

import com.ejercicios.estudianteProgramacion.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByNombreContainingIgnoreCase(String nombre);

}
