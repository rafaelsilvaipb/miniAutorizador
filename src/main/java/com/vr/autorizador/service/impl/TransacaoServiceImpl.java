package com.vr.autorizador.service.impl;

import com.vr.autorizador.dto.CartaoDTO;
import com.vr.autorizador.dto.TransacaoDTO;
import com.vr.autorizador.exception.SaldoInsuficienteException;
import com.vr.autorizador.mapper.CartaoMapper;
import com.vr.autorizador.service.CartaoService;
import com.vr.autorizador.service.TransacaoService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    private final CartaoService cartaoService;

    private final CartaoMapper mapper;

    public TransacaoServiceImpl(CartaoService cartaoService, CartaoMapper mapper) {
        this.cartaoService = cartaoService;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public CartaoDTO transacao(TransacaoDTO transacaoDao) {
        CartaoDTO cartaoDTO = cartaoService.validaExisteCartao(transacaoDao.getNumeroCartao());
        cartaoService.validarSenhaCartao(cartaoDTO, transacaoDao.getSenha());
        validaSaldoInsuficiente(transacaoDao, cartaoDTO);

        cartaoDTO.setSaldoCartao(cartaoDTO.getSaldoCartao().subtract(transacaoDao.getValor()));

        return cartaoService.altera(cartaoDTO);
    }

    private void validaSaldoInsuficiente(TransacaoDTO transacaoDao, CartaoDTO cartaoDao) {
        if (cartaoDao.getSaldoCartao().compareTo(transacaoDao.getValor()) < 0)
            throw new SaldoInsuficienteException();
    }

}