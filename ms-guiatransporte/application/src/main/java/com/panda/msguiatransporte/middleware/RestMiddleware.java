package com.panda.msguiatransporte.middleware;

import com.panda.msguiatransporte.aggregates.exception.PandaAppExceptionNotFound;
import com.panda.msguiatransporte.aggregates.exception.PandaExceptionBadRequest;
import com.panda.msguiatransporte.aggregates.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestMiddleware extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PandaAppExceptionNotFound.class) //maneja las excepciones que se le detallen a la clase que le hemos pasado.
    private ResponseEntity<ResponseError> manejarPandaAppExceptionNotFound(PandaAppExceptionNotFound exception, WebRequest webRequest){
        ResponseError responseError = new ResponseError(new Date(), exception.getMensaje(), webRequest.getDescription(false));
        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PandaExceptionBadRequest.class) //maneja las excepciones que se le detallen a la clase que le hemos pasado.
    private ResponseEntity<ResponseError> manejarPandaAppExceptionBadRequest(PandaExceptionBadRequest exception, WebRequest webRequest){
        ResponseError responseError = new ResponseError(new Date(), exception.getMensaje(), webRequest.getDescription(false));
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class) //maneja las excepciones que se le detallen a la clase que le hemos pasado.
    private ResponseEntity<ResponseError> manejarGlobalException(Exception exception, WebRequest webRequest){
        ResponseError responseError = new ResponseError(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
