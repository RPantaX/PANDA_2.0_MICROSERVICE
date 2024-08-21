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
    @NotBlank
    @Positive
    private Long facturaNumero;
    @NotBlank
    @Size(min = 11, max = 11, message = "El RUC del cliente debe tener exáctamente 11 caracteres.")
    private String clienteRuc;
    @NotBlank
    private String observacion;
    @NotBlank
    @PositiveOrZero
    private BigDecimal descuentos;
    @NotBlank
    @PositiveOrZero
    private BigDecimal anticipios;
    @NotBlank
    @PositiveOrZero
    private BigDecimal isc;
    @NotBlank
    @PositiveOrZero
    private BigDecimal icbper;
    @NotBlank
    @PositiveOrZero
    private BigDecimal otrosCargos;
    @NotBlank
    @PositiveOrZero
    private BigDecimal otrosTributos;
    @NotBlank
    @Size(min = 11, max = 11, message = "El RUC del remitente debe tener exáctamente 11 caracteres.")
    private String emisorRuc;
    @NotBlank(message = "Debe ingresar al menos una guia de transportista.")
    private List<String> guiaTranspSerieNumero;
    @NotBlank(message = "Debe ingresar al menos un detalleFactura.")
    private List<RequestFacturaDetalle> detallesFacturas;

}
