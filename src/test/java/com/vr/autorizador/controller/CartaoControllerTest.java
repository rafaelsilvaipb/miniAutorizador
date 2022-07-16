package com.vr.autorizador.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vr.autorizador.dto.CartaoDTO;
import com.vr.autorizador.service.CartaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class CartaoControllerTest {

    private static final String PATH = "/cartoes";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CartaoService em;

    @Test
    public void criarCartao() throws Exception {
        CartaoDTO cartao = CartaoDTO.builder().numeroCartao("6549873025634500").senha("1233").build();
        CartaoDTO cartaoexpect = cartao;
        cartaoexpect.setSaldoCartao(new BigDecimal(500));

        //Inserindo Cartão
        mockMvc.perform(post(PATH)
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(cartao)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(cartaoexpect)))
                .andReturn();
    }

    @Test
    public void consultarSaldo() throws Exception {
        CartaoDTO cartao = CartaoDTO.builder().numeroCartao("6549873025634500").senha("1233").build();
        CartaoDTO cartaoexpect = cartao;
        cartaoexpect.setSaldoCartao(new BigDecimal(500));

        //Inserindo Cartão
        mockMvc.perform(post(PATH)
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(cartao)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(cartaoexpect)))
                .andReturn();

        //Consultando saldo
        mockMvc.perform(get(PATH.concat("/6549873025634500"))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("500"))
                .andReturn();
    }
}