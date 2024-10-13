package com.panda.seguridad.request;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String dni;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private String username;
    private String password;
}
