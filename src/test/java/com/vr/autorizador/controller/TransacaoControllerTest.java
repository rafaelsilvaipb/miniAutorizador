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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class TransacaoControllerTest {

    private static final String PATH_CARTOES = "/cartoes";
    private static final String PATH_TRANSACOES = "/transacoes";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CartaoService em;

    @Test
    public void transacaoSucesso() throws Exception {
        CartaoDTO cartao = CartaoDTO.builder().numeroCartao("6549873025634500").senha("1233").build();
        CartaoDTO cartaoexpect = CartaoDTO.builder().numeroCartao("6549873025634500").senha("1233").saldoCartao(new BigDecimal(500)).build();
        CartaoDTO cartaoTrans = CartaoDTO.builder().numeroCartao("6549873025634500").senha("1233").saldoCartao(new BigDecimal(30)).build();

        //Inserindo Cartão
        mockMvc.perform(post(PATH_CARTOES)
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(cartao)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(cartaoexpect)))
                .andReturn();

        mockMvc.perform(post(PATH_TRANSACOES)
                        .contentType(APPLICATION_JSON)
                        .content("{\n" +
                                "    \"numeroCartao\": \"6549873025634500\",\n" +
                                "    \"senha\": \"1233\",\n" +
                                "    \"valor\": 30\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"))
                .andReturn();

    }

    @Test
    public void transacaoNaoTemCartao() throws Exception {
        CartaoDTO cartao = CartaoDTO.builder().numeroCartao("6549873025634500").senha("1233").build();
        CartaoDTO cartaoexpect = CartaoDTO.builder().numeroCartao("6549873025634500").senha("1233").saldoCartao(new BigDecimal(500)).build();
        CartaoDTO cartaoTrans = CartaoDTO.builder().numeroCartao("6549873025634500").senha("1233").saldoCartao(new BigDecimal(30)).build();

        //Inserindo Cartão
        mockMvc.perform(post(PATH_CARTOES)
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(cartao)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(cartaoexpect)))
                .andReturn();

        mockMvc.perform(post(PATH_TRANSACOES)
                        .contentType(APPLICATION_JSON)
                        .content("{\n" +
                                "    \"numeroCartao\": \"6549873025634509\",\n" +
                                "    \"senha\": \"1233\",\n" +
                                "    \"valor\": 30\n" +
                                "}"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string("CARTAO_INEXISTENTE"))
                .andReturn();
    }

    @Test
    public void senhaInvalida() throws Exception {
        CartaoDTO cartao = CartaoDTO.builder().numeroCartao("6549873025634500").senha("1233").build();
        CartaoDTO cartaoexpect = CartaoDTO.builder().numeroCartao("6549873025634500").senha("1233").saldoCartao(new BigDecimal(500)).build();
        CartaoDTO cartaoTrans = CartaoDTO.builder().numeroCartao("6549873025634500").senha("1233").saldoCartao(new BigDecimal(30)).build();

        //Inserindo Cartão
        mockMvc.perform(post(PATH_CARTOES)
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(cartao)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(cartaoexpect)))
                .andReturn();

        mockMvc.perform(post(PATH_TRANSACOES)
                        .contentType(APPLICATION_JSON)
                        .content("{\n" +
                                "    \"numeroCartao\": \"6549873025634500\",\n" +
                                "    \"senha\": \"1232\",\n" +
                                "    \"valor\": 30\n" +
                                "}"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string("SENHA_INVALIDA"))
                .andReturn();
    }

    @Test
    public void saldoInsuficiente() throws Exception {
        CartaoDTO cartao = CartaoDTO.builder().numeroCartao("6549873025634500").senha("1233").build();
        CartaoDTO cartaoexpect = CartaoDTO.builder().numeroCartao("6549873025634500").senha("1233").saldoCartao(new BigDecimal(500)).build();
        CartaoDTO cartaoTrans = CartaoDTO.builder().numeroCartao("6549873025634500").senha("1233").saldoCartao(new BigDecimal(30)).build();

        //Inserindo Cartã0
        mockMvc.perform(post(PATH_CARTOES)
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(cartao)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(cartaoexpect)))
                .andReturn();

        mockMvc.perform(post(PATH_TRANSACOES)
                        .contentType(APPLICATION_JSON)
                        .content("{\n" +
                                "    \"numeroCartao\": \"6549873025634500\",\n" +
                                "    \"senha\": \"1233\",\n" +
                                "    \"valor\": 501\n" +
                                "}"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string("SALDO_INSUFICIENTE"))
                .andReturn();
    }
}