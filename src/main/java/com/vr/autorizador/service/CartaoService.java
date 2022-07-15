package com.vr.autorizador.service;


import com.vr.autorizador.dto.CartaoDTO;

import java.math.BigDecimal;

public interface  CartaoService {
    CartaoDTO salva(CartaoDTO cartaoDto);
    CartaoDTO buscarCartao(CartaoDTO cartaoDTO);
    void validarSenhaCartao(CartaoDTO cartaoDto, String senha);
    CartaoDTO validaExisteCartao(String numeroCartao);
    BigDecimal buscaSaldo(String numeroCartao);
    CartaoDTO altera(CartaoDTO cartaoDTO);
}
