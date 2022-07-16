package com.vr.autorizador.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@Table(name = "cartao")
public class CartaoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "numero_cartao")
    private String numeroCartao;

    @Column(name = "senha")
    private String senha;

    @Column(name = "saldo_cartao")
    private BigDecimal saldoCartao;

    @PrePersist
    private void prePersist() {
        this.saldoCartao = BigDecimal.valueOf(500);
    }
}
