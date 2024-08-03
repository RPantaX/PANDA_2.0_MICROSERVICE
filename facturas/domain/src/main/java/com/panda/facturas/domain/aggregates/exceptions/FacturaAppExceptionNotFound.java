package com.panda.facturas.domain.aggregates.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FacturaAppExceptionNotFound extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String mensaje;
}
