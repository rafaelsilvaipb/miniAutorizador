package com.vr.autorizador.repository;


import com.vr.autorizador.domain.CartaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {

    Optional<CartaoEntity> findByNumeroCartao(String numeroCartao);
}