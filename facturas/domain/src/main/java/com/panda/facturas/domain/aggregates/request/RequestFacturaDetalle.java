package com.panda.facturas.domain.aggregates.request;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RequestFacturaDetalle {
    private Integer cantidad;
    private String unidadMedida;
    private String descripcion;
    private BigDecimal valorUnitario;
    private BigDecimal icbper;
}
