package com.ejemplo.tareas.service;
import com.ejemplo.tareas.dto.RegistroDTO;
import com.ejemplo.tareas.dto.LoginDTO;
import com.ejemplo.tareas.model.Usuario;
import com.ejemplo.tareas.model.Rol;
import com.ejemplo.tareas.repository.UsuarioRepository;
import com.ejemplo.tareas.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AutorizaciónServiceImpl implements AutorizaciónService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String registrar(RegistroDTO registroDTO) {
        if (usuarioRepository.existsByUsername(registroDTO.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(registroDTO.getUsername());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
        usuario.setRol(Rol.ROLE_USER); 

        usuarioRepository.save(usuario);

        return "Usuario registrado exitosamente";
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByUsername(loginDTO.getUsername())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return jwtUtil.generarToken(usuario.getUsername(), usuario.getRol().name());
    }

}
