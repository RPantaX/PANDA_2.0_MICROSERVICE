package com.panda.msguiatransporte.aggregates.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
public class PandaAppExceptionNotFound extends RuntimeException {
    private static final long serialVersionUID=1L;
    private String mensaje;
}
