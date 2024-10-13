package com.panda.seguridad.service.impl;

import com.panda.seguridad.entity.Role;
import com.panda.seguridad.entity.Usuario;
import com.panda.seguridad.repository.UsuarioRepository;
import com.panda.seguridad.request.SignInRequest;
import com.panda.seguridad.request.SignUpRequest;
import com.panda.seguridad.response.AuthenticationResponse;
import com.panda.seguridad.service.AuthenticationService;
import com.panda.seguridad.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public Usuario signUpUser(SignUpRequest signUpRequest) {
        if (signUpRequest.getDni() == null) {
            throw new IllegalArgumentException("El DNI no puede ser null");
        }
        Usuario usuario = new Usuario();
        usuario.setDNI(Long.valueOf(signUpRequest.getDni()));
        usuario.setNombres(signUpRequest.getNombres());
        usuario.setApellidos(signUpRequest.getApellidos());
        usuario.setEmail(signUpRequest.getEmail());
        usuario.setTelefono(signUpRequest.getTelefono());
        usuario.setUsername(signUpRequest.getUsername());
        usuario.setRole(Role.USER);
        usuario.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        usuario.setEstado(true);
        usuario.setCreadoEn(getTimestamp());
        usuario.setUsuarioModificador("prueba");
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario signUpAdmin(SignUpRequest signUpRequest) {

        if (signUpRequest.getDni() == null) {
            throw new IllegalArgumentException("El DNI no puede ser null");
        }
        Usuario usuario = new Usuario();
        usuario.setDNI(Long.valueOf(signUpRequest.getDni()));
        usuario.setNombres(signUpRequest.getNombres());
        usuario.setApellidos(signUpRequest.getApellidos());
        usuario.setEmail(signUpRequest.getEmail());
        usuario.setTelefono(signUpRequest.getTelefono());
        usuario.setUsername(signUpRequest.getUsername());
        usuario.setRole(Role.ADMIN);
        usuario.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        usuario.setEstado(true);
        usuario.setCreadoEn(getTimestamp());
        usuario.setUsuarioModificador("prueba");
        return usuarioRepository.save(usuario);
    }

    @Override
    public AuthenticationResponse signin(SignInRequest signInRequest) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(),signInRequest.getPassword())); //METODO PARA AUTHENTICATION,
        var user = usuarioRepository.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Username no valido"));

        var jwt = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse =  new AuthenticationResponse();
        authenticationResponse.setToken(jwt);
        return authenticationResponse;
    }
    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }
}
