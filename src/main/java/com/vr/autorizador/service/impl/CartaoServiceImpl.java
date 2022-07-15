package com.vr.autorizador.service.impl;

import com.vr.autorizador.domain.CartaoEntity;
import com.vr.autorizador.dto.CartaoDTO;
import com.vr.autorizador.exception.CartaoExistenteException;
import com.vr.autorizador.exception.SenhaInvalidaException;
import com.vr.autorizador.mapper.CartaoMapper;
import com.vr.autorizador.repository.CartaoRepository;
import com.vr.autorizador.service.CartaoService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class CartaoServiceImpl implements CartaoService {

    private final CartaoRepository repository;
    private final CartaoMapper mapper;

    public CartaoServiceImpl(CartaoRepository repository, CartaoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CartaoDTO cadastrarCartao(CartaoDTO cartaoDTO) {
        buscarCartao(cartaoDTO.getNumeroCartao());
        CartaoEntity save = repository.save(mapper.dtoToEntity(cartaoDTO));
        return mapper.entityToDto(save);
    }


    @Override
    public CartaoDTO buscarCartao(String numeroCartao) {
        CartaoEntity cartaoEntity = repository.findByNumeroCartao(numeroCartao)
                .orElseThrow(() -> new CartaoExistenteException("Cartão " + numeroCartao + " não existe"));

        return mapper.entityToDto(cartaoEntity);
    }

    @Override
    public void validarSenhaCartao(CartaoDTO cartaoDto, String senha) {
        if (!cartaoDto.getSenha().equals(senha)) {
            throw new SenhaInvalidaException();
       }
    }
}