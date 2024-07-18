package com.panda.facturas.domain.aggregates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacturaDetalleDTO {
    private Long facturaNumero;
    private String facturaSerienumero;
    private String facturaSerie;

    private Integer cantidad;

    private String unidadMedida;

    private String descripcion;

    private BigDecimal valorUnitario;

    private BigDecimal icbper;
}
