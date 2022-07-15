package com.vr.autorizador.service;

import com.vr.autorizador.dto.CartaoDTO;
import com.vr.autorizador.dto.TransacaoDTO;

public interface TransacaoService {

    CartaoDTO transacao(TransacaoDTO transacaoDTO);
}
