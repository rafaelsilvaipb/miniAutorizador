package com.vr.autorizador.service;


import com.vr.autorizador.dto.CartaoDTO;

public interface  CartaoService {
    CartaoDTO cadastrarCartao(CartaoDTO cartaoDto);

    CartaoDTO buscarCartao(String numeroCartao);

    void validarSenhaCartao(CartaoDTO cartaoDto, String senha);
}
