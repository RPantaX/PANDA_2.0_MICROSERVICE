package com.panda.facturas.infraestructure.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "emisor")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmisorEntity {
    @Id
    @Column(name = "emisor_ruc", length = 11)
    private String emisorRuc;

    @Column(name = "emisor_razon_social", nullable = false, length = 255)
    private String emisorRazonSocial;

    @Column(name = "emisor_direccion", nullable = false, length = 255)
    private String emisorDireccion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "usuario_modificador", nullable = false, length = 15)
    private String usuarioModificador;

    @Column(name = "creado_en", nullable = false)
    private Timestamp creadoEn;

    @Column(name = "modificado_en", nullable = true)
    private Timestamp modificadoEn;

    @Column(name = "eliminado_en", nullable = true)
    private Timestamp eliminadoEn;
}
