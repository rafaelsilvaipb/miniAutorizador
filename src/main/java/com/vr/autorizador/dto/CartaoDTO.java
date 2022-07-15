package com.vr.autorizador.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoDTO {
    @JsonIgnore
    private Long id;
    private String numeroCartao;
    private String senha;
    private BigDecimal saldoCartao;
}
