package com.panda.facturas.domain.aggregates.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RequestFactura {

    @Positive
    private Long facturaNumero;
    @NotBlank
    @Size(min = 11, max = 11, message = "El RUC del cliente debe tener exáctamente 11 caracteres.")
    private String clienteRuc;
    @NotBlank
    private String observacion;

    @PositiveOrZero
    private BigDecimal descuentos;
    @PositiveOrZero
    private BigDecimal anticipios;

    @PositiveOrZero
    private BigDecimal isc;

    @PositiveOrZero
    private BigDecimal icbper;

    @PositiveOrZero
    private BigDecimal otrosCargos;

    @PositiveOrZero
    private BigDecimal otrosTributos;
    @NotBlank
    @Size(min = 11, max = 11, message = "El RUC del remitente debe tener exáctamente 11 caracteres.")
    private String emisorRuc;
    @NotNull
    private List<String> guiaTranspSerieNumero;

    private List<RequestFacturaDetalle> detallesFacturas;

}
