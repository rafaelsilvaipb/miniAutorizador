package com.vr.autorizador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CartaoNaoExistenteException extends RuntimeException {

    public CartaoNaoExistenteException(String message) {
        super(message);
    }
}