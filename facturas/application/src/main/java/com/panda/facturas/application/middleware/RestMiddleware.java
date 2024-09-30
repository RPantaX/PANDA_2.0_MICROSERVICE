package com.panda.facturas.application.middleware;

import com.panda.facturas.domain.aggregates.exceptions.FacturaAppExceptionBadRequest;
import com.panda.facturas.domain.aggregates.exceptions.FacturaAppExceptionNotFound;
import com.panda.facturas.domain.aggregates.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestMiddleware extends ResponseEntityExceptionHandler {

    @ExceptionHandler({FacturaAppExceptionBadRequest.class})
    private ResponseEntity<ResponseError> manejarFacturaAppExceptionBadRequest(FacturaAppExceptionBadRequest f, WebRequest request){
        ResponseError responseError = new ResponseError(new Date(), f.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({FacturaAppExceptionNotFound.class})
    private ResponseEntity <ResponseError> manejarFacturaAppExceptionNotFound(FacturaAppExceptionNotFound f, WebRequest request){
        ResponseError responseError = new ResponseError(new Date(), f.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    private ResponseEntity<ResponseError> manejarGlobalException(Exception ex, WebRequest request) {
        ResponseError error = new ResponseError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}