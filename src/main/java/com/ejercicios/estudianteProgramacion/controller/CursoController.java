package com.ejercicios.estudianteProgramacion.controller;

import com.ejercicios.estudianteProgramacion.dto.CursoDTO;
import com.ejercicios.estudianteProgramacion.dto.TemaDTO;
import com.ejercicios.estudianteProgramacion.model.Curso;
import com.ejercicios.estudianteProgramacion.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/estudiante/cursos")
public class CursoController {

    @Autowired
    private ICursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoDTO> crearCurso(@RequestBody CursoDTO cursoDto) {
        CursoDTO cursoCreado = cursoService.crearCurso(cursoDto);
        return ResponseEntity.created(URI.create("/estudiante/cursos/" + cursoCreado.getIdCurso())).body(cursoCreado);
    }

    @GetMapping
    public ResponseEntity<List<CursoDTO>> traerCursos(@RequestParam(required = false) String nombre) {

        if (nombre == null) return ResponseEntity.ok(cursoService.traerCursos());

        return ResponseEntity.ok(cursoService.traerCursosPorNombre(nombre));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> traerCurso(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.traerCurso(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> actualizarCurso(@PathVariable Long id, @RequestBody CursoDTO cursoDto) {
        return ResponseEntity.ok(cursoService.actualizarCurso(id, cursoDto));
    }

    @DeleteMapping("/{id}")
    public void eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
    }

    @GetMapping("/{id}/temas")
    public ResponseEntity<List<TemaDTO>> traerTemasPorCurso(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.traerTemasPorCurso(id));
    }

}
