package com.panda.msguiatransporte.aggregates.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RequestGuiaTranspt {
    private Long guiaTransptNumero;
    private Timestamp fechaInicioTraslado;

    private String registroMTC;

    private String partida;

    private String llegada;

    private String unidadMedidaPesoBruto;

    private BigDecimal pesoBrutoTotal;

    private Boolean transbordoProgramado;

    private Boolean retornoVehiculoVacio;

    private Boolean transporteSubcontratado;

    private String destRuc;

    private String remitenteRuc;

    private String pagFleteRuc;

    private String condDocIdentidad;

}
