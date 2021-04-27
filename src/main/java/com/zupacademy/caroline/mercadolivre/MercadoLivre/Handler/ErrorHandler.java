package com.zupacademy.caroline.mercadolivre.MercadoLivre.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErro methodArgumentNotValidException(MethodArgumentNotValidException e){
        return new ApiErro(e.getBindingResult().getFieldErrors().stream()
                .map(Erros::new).collect(Collectors.toList()));
    }
}
