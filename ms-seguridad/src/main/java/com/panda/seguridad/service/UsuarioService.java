package com.panda.seguridad.service;


import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService {
    UserDetailsService userDetailsService();
}
