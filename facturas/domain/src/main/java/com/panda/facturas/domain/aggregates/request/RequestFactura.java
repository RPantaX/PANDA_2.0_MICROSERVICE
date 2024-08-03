package com.panda.facturas.domain.aggregates.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RequestFactura {
    private Long facturaNumero;
    private String clienteRuc;
    private String observacion;
    private BigDecimal descuentos;
    private BigDecimal anticipios;
    private BigDecimal isc;
    private BigDecimal icbper;
    private BigDecimal otrosCargos;
    private BigDecimal otrosTributos;
    private String emisorRuc;
    private List<String> guiaTranspSerieNumero;
    private List<RequestFacturaDetalle> detallesFacturas;

}
