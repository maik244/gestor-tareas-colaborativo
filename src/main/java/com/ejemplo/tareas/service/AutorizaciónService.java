package com.ejemplo.tareas.service;

import com.ejemplo.tareas.dto.LoginDTO;
import com.ejemplo.tareas.dto.RegistroDTO;

public interface  AutorizaciónService {
String registrar(RegistroDTO registroDTO);
String login(LoginDTO loginDTO);

}
