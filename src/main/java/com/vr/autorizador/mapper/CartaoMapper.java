package com.vr.autorizador.mapper;

import com.vr.autorizador.domain.CartaoEntity;
import com.vr.autorizador.dto.CartaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartaoMapper  {
    CartaoMapper INSTANCE = Mappers.getMapper( CartaoMapper.class );

    @Mapping(source = "numeroCartao", target = "numeroCartao")
    @Mapping(source = "senha", target = "senha")
    @Mapping(source = "saldoCartao", target = "saldoCartao")
    CartaoDTO entityToDto(CartaoEntity entity);

    @Mapping(source = "numeroCartao", target = "numeroCartao")
    @Mapping(source = "senha", target = "senha")
    @Mapping(source = "saldoCartao", target = "saldoCartao")
    CartaoEntity dtoToEntity(CartaoDTO dto);

}