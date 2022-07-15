package com.vr.autorizador.controller;

import com.vr.autorizador.dto.TransacaoDTO;
import com.vr.autorizador.service.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody TransacaoDTO transacaoDao) {
        transacaoService.transacao(transacaoDao);
        return ResponseEntity.ok("OK");
    }
}
