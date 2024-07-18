package com.panda.facturas.domain.aggregates.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestFacturaDetalle {
    private Integer cantidad;
    private String unidadMedida;
    private String descripcion;
    private BigDecimal valorUnitario;
    private BigDecimal icbper;
}
