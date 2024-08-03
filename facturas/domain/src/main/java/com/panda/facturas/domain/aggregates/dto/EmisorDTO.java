package com.panda.facturas.domain.aggregates.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmisorDTO {
    private String emisorRuc;

    private String emisorRazonSocial;

    private String emisorDireccion;

    private Boolean estado;

    private String usuarioModificador;

    private Timestamp creadoEn;

    private Timestamp modificadoEn;

    private Timestamp eliminadoEn;
}
