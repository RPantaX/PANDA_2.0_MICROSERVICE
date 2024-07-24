package com.panda.msguiatransporte.aggregates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemitenteDTO {
    private String remitenteRuc;

    private String remitenteRazonSocial;

    private String remitenteDireccion;

    private Boolean estado;

    private String usuarioModificador;

    private Timestamp creadoEn;

    private Timestamp modificadoEn;

    private Timestamp eliminadoEn;
}
