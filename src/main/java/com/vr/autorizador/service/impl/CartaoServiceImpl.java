package com.vr.autorizador.service.impl;

import com.vr.autorizador.domain.CartaoEntity;
import com.vr.autorizador.dto.CartaoDTO;
import com.vr.autorizador.exception.CartaoNaoExistenteException;
import com.vr.autorizador.exception.SenhaInvalidaException;
import com.vr.autorizador.mapper.CartaoMapper;
import com.vr.autorizador.repository.CartaoRepository;
import com.vr.autorizador.service.CartaoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartaoServiceImpl implements CartaoService {

    private final CartaoRepository repository;

    public CartaoServiceImpl(CartaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public CartaoDTO salva(CartaoDTO cartaoDTO) {
        CartaoDTO busca =  buscarCartao(cartaoDTO);
        CartaoEntity save = repository.save(CartaoMapper.dtoToEntity(busca));
        return CartaoMapper.entityToDto(save);
    }

    @Override
    public CartaoDTO altera(CartaoDTO cartaoDTO) {
        CartaoEntity save = repository.save(CartaoMapper.dtoToEntity(cartaoDTO));
        return CartaoMapper.entityToDto(save);
    }


    @Override
    public CartaoDTO buscarCartao(CartaoDTO cartaoDTO) {
        CartaoEntity cartaoEntity = repository.findByNumeroCartao(cartaoDTO.getNumeroCartao())
                .orElse(CartaoMapper.dtoToEntity(cartaoDTO));

        return CartaoMapper.entityToDto(cartaoEntity);
    }

    @Override
    public BigDecimal buscaSaldo(String numeroCartao) {
        return validaExisteCartao(numeroCartao).getSaldoCartao();
    }

    @Override
    public void validarSenhaCartao(CartaoDTO cartaoDto, String senha) {
        if (!cartaoDto.getSenha().equals(senha)) {
            throw new SenhaInvalidaException();
       }
    }

    @Override
    public CartaoDTO validaExisteCartao(String numeroCartao) {
       return CartaoMapper.entityToDto(repository.findByNumeroCartao(numeroCartao)
                .orElseThrow(() -> new CartaoNaoExistenteException()));
    }
}