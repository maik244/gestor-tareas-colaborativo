package com.ejemplo.tareas.controller;

import com.ejemplo.tareas.dto.TareaDTO;
import com.ejemplo.tareas.model.Tarea;
import com.ejemplo.tareas.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/tareas")
public class TareaController {
    
    @Autowired
    private TareaService tareaService;

    @PostMapping
    public Tarea crearTarea(@RequestBody TareaDTO tareaDTO) {
    Tarea tarea = new Tarea(
        tareaDTO.getTitulo(),
        tareaDTO.getDescripcion(),
        tareaDTO.isCompletada()
    );
    return tareaService.crearTarea(tarea);

    }

    @GetMapping
    public List<Tarea> obtenerTareas() {
        return tareaService.obtenerTareas();
    }

    @GetMapping("/{id}")
    public Tarea obtenerTareaPorId(@PathVariable Long id) {
        return tareaService.obtenerTareaPorId(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Long id) {
        tareaService.eliminarTarea(id);
    }
}   
