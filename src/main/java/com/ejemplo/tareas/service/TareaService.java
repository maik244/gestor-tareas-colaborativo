package com.ejemplo.tareas.service;


import com.ejemplo.tareas.model.Tarea;
import java.util.List;
import java.util.Optional;

public interface TareaService {
    Tarea crearTarea(Tarea tarea);
    List<Tarea> obtenerTareas();
    Optional<Tarea> obtenerTareaPorId(Long id);
    void eliminarTarea(Long id);
}
