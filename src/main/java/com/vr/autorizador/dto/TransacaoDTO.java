package com.vr.autorizador.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransacaoDTO {
    private String numeroCartao;
    private String senha;
    private BigDecimal valor;
}