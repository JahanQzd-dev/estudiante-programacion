package com.ejercicios.estudianteProgramacion.service;

import com.ejercicios.estudianteProgramacion.dto.TemaDTO;
import com.ejercicios.estudianteProgramacion.exception.NotFoundException;
import com.ejercicios.estudianteProgramacion.mapper.Mapper;
import com.ejercicios.estudianteProgramacion.model.Curso;
import com.ejercicios.estudianteProgramacion.model.Tema;
import com.ejercicios.estudianteProgramacion.repository.CursoRepository;
import com.ejercicios.estudianteProgramacion.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemaService implements ITemaService{

    @Autowired
    private TemaRepository temaRepo;

    @Autowired
    private CursoRepository cursoRepo;

    @Override
    public List<TemaDTO> traerTemas() {
        return temaRepo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public TemaDTO traerTema(Long id) {

        // Verificar que el tema exista y traerlo
        Tema tema = temaRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Tema no encontrado"));

        // Convertir a TemaDTO y retornar
        return Mapper.toDTO(tema);
    }

    @Override
    public TemaDTO crearTema(TemaDTO temaDto) {
        // Verificar si el curso existe
        Curso curso = cursoRepo.findById(temaDto.getIdCurso())
                .orElseThrow(() -> new NotFoundException("Curso no encontrado"));

        // Construir objeto Tema a partir del TemaDTO ingresado
        Tema tema =Tema.builder()
                .nombre(temaDto.getNombre())
                .descripcion(temaDto.getDescripcion())
                .curso(curso)
                .build();

        // Guardar Tema y retornar ProductoDTO
        return Mapper.toDTO(temaRepo.save(tema));
    }

    @Override
    public TemaDTO actualizarTema(Long id, TemaDTO temaDto) {

        // Verificar que el tema exista y traerlo
        Tema tema = temaRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Tema no encontrado"));

        // Settear los nuevos valores al tema
        if(temaDto.getNombre() != null)  tema.setNombre(temaDto.getNombre());
        if(temaDto.getDescripcion() != null) tema.setDescripcion(temaDto.getDescripcion());

        if(temaDto.getIdCurso() != null) {
            // Verificar si el curso existe
            Curso curso = cursoRepo.findById(temaDto.getIdCurso())
                            .orElseThrow(() -> new NotFoundException("Curso no encontrado"));

            tema.setCurso(curso);
        }

        // Actualizar y retornar
        return Mapper.toDTO(temaRepo.save(tema));
    }

    @Override
    public void eliminarTema(Long id) {

       Tema tema = temaRepo.findById(id)
               .orElseThrow(() -> new NotFoundException("Tema no encontrado"));

       temaRepo.delete(tema);
    }
}
