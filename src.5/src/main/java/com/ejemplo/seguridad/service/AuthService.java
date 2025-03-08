package com.ejemplo.seguridad.service;

import com.ejemplo.seguridad.model.Usuario;
import com.ejemplo.seguridad.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registrar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }

    public boolean validarUsuario(Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findByUsername(usuario.getUsername()).orElse(null);
        return usuarioExistente != null && passwordEncoder.matches(usuario.getPassword(), usuarioExistente.getPassword());
    }
}