package com.tcg.lista.application.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    final private StandardError erroRetorno = new StandardError();

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> EntityNotFoundException(EntityNotFoundException erro, HttpServletRequest request){

        erroRetorno.setTimestamp(Instant.now());
        erroRetorno.setStatus(HttpStatus.NOT_FOUND.value());
        erroRetorno.setError("Entity not Found");
        erroRetorno.setMessage(erro.getMessage());
        erroRetorno.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(erroRetorno);
    }
}
