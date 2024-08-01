package com.panda.msguiatransporte.aggregates.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DestinatarioDTO {
    private String destRuc;

    private String destRazonSocial;

    private String destDireccion;

    private Boolean estado;

    private String usuarioModificador;

    private Timestamp creadoEn;

    private Timestamp modificadoEn;

    private Timestamp eliminadoEn;
}
