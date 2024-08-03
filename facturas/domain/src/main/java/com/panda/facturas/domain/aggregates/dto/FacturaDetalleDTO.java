package com.panda.facturas.domain.aggregates.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FacturaDetalleDTO {
    private Long facturaNumero;

    private String facturaSerie;

    private String facturaSerienumero;

    private Integer cantidad;

    private String unidadMedida;

    private String descripcion;

    private BigDecimal valorUnitario;

    private BigDecimal icbper;
}
