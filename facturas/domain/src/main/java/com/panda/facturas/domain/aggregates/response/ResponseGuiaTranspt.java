package com.panda.facturas.domain.aggregates.response;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseGuiaTranspt {
    private Long guiaTranspNumero;

    private String guiaTranspSerie;

    private String guiaTranspSerienumero;

    private Timestamp fechaEmision;

    private Timestamp fechaInicioTraslado;

    private String registroMTC;

    private String partida;

    private String llegada;

    private String unidadMedidaPesoBruto;

    private BigDecimal pesoBrutoTotal;

    private Boolean transbordoProgramado;

    private Boolean retornoVehiculoVacio;

    private Boolean transporteSubcontratado;

    private String usuarioEmisor;

    private String destRuc;

    private String remitenteRuc;

    private String pagFleteRuc;

    private String facturaSerienumero;

    private String condDocIdentidad;

    private Integer viajeProgId;
}
