package com.panda.facturas.domain.aggregates.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DetraccionDTO {

    private Long facturaNumero;

    private String serienumero;

    private String facturaSerie;

    private String leyenda;

    private String servicio;

    private String medioPago;

    private String nroCuenta;

    private Integer detraccionPorcentaje;

    private BigDecimal detraccionMonto;

    private Timestamp fecha;

    private String usuarioModificador;
}
