package com.vr.autorizador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "SENHA_INVALIDA")
public class SenhaInvalidaException extends RuntimeException {
}