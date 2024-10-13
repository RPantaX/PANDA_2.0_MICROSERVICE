package com.panda.seguridad.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@RequiredArgsConstructor
//Implementamos UserDetails para tener los metodos de este
public class Usuario implements UserDetails {
    @Id
    private Long DNI;
    @Column(name = "nombres", nullable = false, unique=true)
    private String nombres;
    @Column(name = "apellidos", nullable = false)
    private String apellidos;
    @Column(name = "email", nullable = false, unique=true)
    private String email;
    @Column(name = "telefono", nullable = true)
    private String telefono;
    @Column(name = "username", nullable = false, unique=true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "usuario_modificador", nullable = false, length = 15)
    private String usuarioModificador;

    @Column(name = "creado_en", nullable = false)
    private Timestamp creadoEn;

    @Column(name = "modificado_en")
    private Timestamp modificadoEn;

    @Column(name = "eliminado_en")
    private Timestamp eliminadoEn;

    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getUsername(){
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return estado;
    }
}
