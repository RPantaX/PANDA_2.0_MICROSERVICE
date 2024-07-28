package com.panda.msguiatransporte.aggregates.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PandaExceptionBadRequest extends RuntimeException {
    private static final long serialVersionUID=1L;
    private String mensaje;
}
