package com.vr.autorizador.controller;


import com.vr.autorizador.dto.CartaoDTO;
import com.vr.autorizador.service.CartaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/cartoes")
public class CartaoController {

    private final CartaoService cartaoService;

    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @PostMapping
    public ResponseEntity<CartaoDTO> salvar(@RequestBody CartaoDTO cartaoDao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartaoService.salva(cartaoDao));
    }

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<BigDecimal> buscarSaldo(@PathVariable String numeroCartao ) {
        return ResponseEntity.ok(cartaoService.buscaSaldo(numeroCartao));
    }
}
