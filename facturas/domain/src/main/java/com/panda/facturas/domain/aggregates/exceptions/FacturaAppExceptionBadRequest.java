package com.panda.facturas.domain.aggregates.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FacturaAppExceptionBadRequest extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;
}
