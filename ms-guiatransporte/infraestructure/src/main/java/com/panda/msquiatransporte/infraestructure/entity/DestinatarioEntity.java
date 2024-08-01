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
public class DestinatarioEntity {
    @Id
    @Column(name = "dest_ruc", length = 11)
    private String destRuc;

    @Column(name = "dest_razon_social", nullable = false, length = 255)
    private String destRazonSocial;

    @Column(name = "dest_direccion", nullable = false, length = 255)
    private String destDireccion;

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
}
