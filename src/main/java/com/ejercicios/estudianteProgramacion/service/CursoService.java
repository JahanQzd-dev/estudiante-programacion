package com.ejercicios.estudianteProgramacion.service;

import com.ejercicios.estudianteProgramacion.dto.CursoDTO;
import com.ejercicios.estudianteProgramacion.dto.TemaDTO;
import com.ejercicios.estudianteProgramacion.exception.NotFoundException;
import com.ejercicios.estudianteProgramacion.mapper.Mapper;
import com.ejercicios.estudianteProgramacion.model.Curso;
import com.ejercicios.estudianteProgramacion.model.Tema;
import com.ejercicios.estudianteProgramacion.repository.CursoRepository;
import com.ejercicios.estudianteProgramacion.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService implements ICursoService{

    @Autowired
    private CursoRepository cursoRepo;
    @Autowired
    private TemaRepository temaRepo;

    @Override
    public List<CursoDTO> traerCursos() {
        return cursoRepo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public CursoDTO traerCurso(Long id) {

        // Verificar que el curso exista y traerlo
        Curso curso = cursoRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso no encontrado"));

        // Convertir a CursoDTO y retornar
        return Mapper.toDTO(curso);
    }

    @Override
    public CursoDTO crearCurso(CursoDTO cursoDto) {

        // Validaciones iniciales
        if (cursoDto == null) throw new RuntimeException("CursoDTO es null");

        // Crear el curso
        Curso curso = new Curso();
        curso.setNombre(cursoDto.getNombre());
        curso.setModalidad(cursoDto.getModalidad());
        curso.setFechaFinalizacion(cursoDto.getFechaFinalizacion());

        // Guardar y retornar DTO
        return Mapper.toDTO(cursoRepo.save(curso));
    }

    @Override
    public CursoDTO actualizarCurso(Long id, CursoDTO cursoDto) {

        // Verificar que el curso exista y traer
        Curso curso = cursoRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso no encontrado"));

        // Settear datos
        if(cursoDto.getNombre() != null) curso.setNombre(cursoDto.getNombre());
        if(cursoDto.getModalidad() != null) curso.setModalidad(cursoDto.getModalidad());
        if(cursoDto.getFechaFinalizacion() != null) curso.setFechaFinalizacion(cursoDto.getFechaFinalizacion());

        if(cursoDto.getListaTemas() != null) {

            // Quitar la relación anterior
            for (Tema t: curso.getListaTemas()) {
                t.setCurso(null);
            }

            // Limpiar la lista en curso
            curso.getListaTemas().clear();

            //Agregar nuevos temas
            List<Tema> temas = new ArrayList<>();
            for (TemaDTO temaDto : cursoDto.getListaTemas()) {
                // Validación
                if (temaDto.getIdTema() == null) {
                    throw new RuntimeException("idTema no puede ser null");
                }

                // Buscar tema por id
                Tema tema = temaRepo.findById(temaDto.getIdTema())
                        .orElseThrow(() -> new NotFoundException("Tema no encontrado"));

                // Actualizar el tema
                tema.setCurso(curso);

                // Agregar a la lista temas
                curso.getListaTemas().add(tema);
            }
        }

        // Actualizar y retornar
        return Mapper.toDTO(cursoRepo.save(curso));
    }

    @Override
    public void eliminarCurso(Long id) {

        Curso curso = cursoRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso no encontrado"));

        cursoRepo.delete(curso);
    }

    @Override
    public List<TemaDTO> traerTemasPorCurso(Long id) {
        // Verificar que el curso exista y traerlo
        Curso curso = cursoRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso no encontrado"));

        // Traer todos los temas del curso y retornar
        List<Tema> listaTemas = curso.getListaTemas();

        if(listaTemas.isEmpty()) return Collections.emptyList();

        return listaTemas.stream()
                .map(Mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CursoDTO> traerCursosPorNombre(String nombre) {
        // Traer todos los cursos que tengan la palabra
        List<Curso> listaCursos = cursoRepo.findByNombreContainingIgnoreCase(nombre);

        // Mapear a DTO y retornar
        return listaCursos.stream()
                .map(Mapper::toDTO)
                .collect(Collectors.toList());
    }
}
