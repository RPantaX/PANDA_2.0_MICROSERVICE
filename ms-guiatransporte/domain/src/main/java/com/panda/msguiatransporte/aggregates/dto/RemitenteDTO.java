package com.panda.msguiatransporte.aggregates.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
