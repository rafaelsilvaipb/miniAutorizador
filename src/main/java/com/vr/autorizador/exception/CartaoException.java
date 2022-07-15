package com.vr.autorizador.exception;

import com.vr.autorizador.enums.ExceptionsEnuns;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CartaoException {

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<?> handlerInsuficiente(SaldoInsuficienteException fee) {
        return new ResponseEntity<>(ExceptionsEnuns.SALDO_INSUFICIENTE.name(), HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @ExceptionHandler(SenhaInvalidaException.class)
    public ResponseEntity<?> handlerSenha(SenhaInvalidaException fee) {
        return new ResponseEntity<>(ExceptionsEnuns.SENHA_INVALIDA.name(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(CartaoNaoExistenteException.class)
    public ResponseEntity<?> handlerSenha(CartaoNaoExistenteException fee) {
        return new ResponseEntity<>(ExceptionsEnuns.CARTAO_INEXISTENTE.name(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

}