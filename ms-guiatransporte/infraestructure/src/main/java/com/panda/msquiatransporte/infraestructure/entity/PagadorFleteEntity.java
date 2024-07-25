package com.panda.msquiatransporte.infraestructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Pagador_flete")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagadorFleteEntity {
    @Id
    @Column(name = "pag_flete_ruc", length = 11)
    private String pagFleteRuc;

    @Column(name = "pag_flete_razon_social", nullable = false, length = 255)
    private String pagFleteRazonSocial;

    @Column(name = "pag_flete_direccion", nullable = false, length = 255)
    private String pagFleteDireccion;
    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "usuario_modificador", nullable = false, length = 15)
    private String usuarioModificador;

    @Column(name = "creado_en", nullable = false)
    private Timestamp creadoEn;

    @Column(name = "modificado_en", nullable = false)
    private Timestamp modificadoEn;

    @Column(name = "eliminado_en", nullable = false)
    private Timestamp eliminadoEn;
}
