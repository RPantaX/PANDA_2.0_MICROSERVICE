package com.panda.msquiatransporte.infraestructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Destinatario")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemitenteEntity {
    @Id
    @Column(name = "remitente_ruc", length = 11)
    private String remitenteRuc;

    @Column(name = "remitente_razon_social", nullable = false, length = 255)
    private String remitenteRazonSocial;

    @Column(name = "remitente_direccion", nullable = false, length = 255)
    private String remitenteDireccion;

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
