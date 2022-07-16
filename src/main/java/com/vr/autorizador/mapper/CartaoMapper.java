package com.vr.autorizador.mapper;

import com.vr.autorizador.domain.CartaoEntity;
import com.vr.autorizador.dto.CartaoDTO;
import org.springframework.stereotype.Component;

@Component
public class CartaoMapper {

    public static CartaoEntity dtoToEntity(CartaoDTO cartaoDTO){
        return CartaoEntity.builder()
                .id(cartaoDTO.getId())
                .numeroCartao(cartaoDTO.getNumeroCartao())
                .saldoCartao(cartaoDTO.getSaldoCartao())
                .senha(cartaoDTO.getSenha())
                .build();
    }

    public static CartaoDTO entityToDto(CartaoEntity cartaoEntity){
        return CartaoDTO.builder()
                .id(cartaoEntity.getId())
                .numeroCartao(cartaoEntity.getNumeroCartao())
                .saldoCartao(cartaoEntity.getSaldoCartao())
                .senha(cartaoEntity.getSenha())
                .build();
    }

}