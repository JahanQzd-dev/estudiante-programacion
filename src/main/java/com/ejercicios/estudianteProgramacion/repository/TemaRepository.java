package com.ejercicios.estudianteProgramacion.repository;

import com.ejercicios.estudianteProgramacion.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemaRepository extends JpaRepository<Tema, Long> {

    // Buscar tema por nombre
    Optional<Tema> findByNombre(String nombre);

}
