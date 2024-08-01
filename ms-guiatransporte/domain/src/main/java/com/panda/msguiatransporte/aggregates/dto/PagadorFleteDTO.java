package com.panda.msguiatransporte.aggregates.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PagadorFleteDTO {
    private String pagFleteRuc;

    private String pagFleteRazonSocial;

    private String pagFleteDireccion;

    private String email;

    private Boolean estado;

    private String usuarioModificador;

    private Timestamp creadoEn;

    private Timestamp modificadoEn;

    private Timestamp eliminadoEn;
}
