package com.panda.seguridad.service.impl;

import com.panda.seguridad.entity.Usuario;
import com.panda.seguridad.repository.UsuarioRepository;
import com.panda.seguridad.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return usuarioRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado"));
            }
        };
    }

}
