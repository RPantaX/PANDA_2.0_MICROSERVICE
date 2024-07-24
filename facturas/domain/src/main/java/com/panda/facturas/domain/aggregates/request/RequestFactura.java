package com.panda.facturas.domain.aggregates.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RequestFactura {
    private String clienteRuc;
    private String observacion;
    private Long facturaSerie;
    private BigDecimal descuentos;
    private BigDecimal anticipios;
    private BigDecimal isc;
    private BigDecimal icbper;
    private BigDecimal otrosCargos;
    private BigDecimal otrosTributos;
    private String emisorRuc;
    private int guiaTranspNumero;
    private List<RequestFacturaDetalle> detallesFacturas;
}
