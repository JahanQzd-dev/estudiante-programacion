package com.ejercicios.estudianteProgramacion.controller;

import com.ejercicios.estudianteProgramacion.dto.TemaDTO;
import com.ejercicios.estudianteProgramacion.service.ITemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/estudiante/temas")
public class TemaController {

    @Autowired
    private ITemaService temaService;

    @GetMapping
    public ResponseEntity<List<TemaDTO>> traerTemas() {
        return ResponseEntity.ok(temaService.traerTemas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemaDTO> traerTema(@PathVariable Long id){
        return ResponseEntity.ok(temaService.traerTema(id));
    }

    @PostMapping
    public ResponseEntity<TemaDTO> crearTema(@RequestBody TemaDTO temaDto) {
        TemaDTO temaCreado = temaService.crearTema(temaDto);
        return ResponseEntity.created(URI.create("/estudiante/temas/" + temaCreado.getIdTema())).body(temaCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemaDTO> actualizarTema(@PathVariable Long id, @RequestBody TemaDTO temaDto) {
        return ResponseEntity.ok(temaService.actualizarTema(id, temaDto));
    }

    @DeleteMapping("/{id}")
    public void eliminarTema(@PathVariable Long id) {
        temaService.eliminarTema(id);
    }

}
