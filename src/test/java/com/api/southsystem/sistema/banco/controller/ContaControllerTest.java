package com.api.southsystem.sistema.banco.controller;

import com.api.southsystem.sistema.banco.builder.ProdutoFinanceiroBuilder;
import com.api.southsystem.sistema.banco.dto.ContaDto;
import com.api.southsystem.sistema.banco.enums.TipoConta;
import com.api.southsystem.sistema.banco.services.ContaServices;
import com.api.southsystem.sistema.banco.services.PessoaServices;
import com.api.southsystem.sistema.banco.services.ProdutoFinanceiroServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PessoaController.class)
public class ContaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ContaServices service;

    @MockBean
    private ProdutoFinanceiroServices produtoFinanceiroServices;

    @MockBean
    private PessoaServices pessoaServices;

    private List<ContaDto> contasDtos;

    @BeforeEach
    void setUp(){

        this.contasDtos = Arrays.asList(
                new ContaDto("001", "55", TipoConta.CONTA_CORRENTE, ProdutoFinanceiroBuilder.criarListaDeProdutosFinanceiroDto()),
                new ContaDto("002", "55", TipoConta.CONTA_EMPRESARIAL, ProdutoFinanceiroBuilder.criarListaDeProdutosFinanceiroDto())
        );
    }

    @Test
    @DisplayName("Carregar todas as contas com sucesso")
    void carregarTodasAsContas() throws Exception{

        when(service.buscarContas()).thenReturn(this.contasDtos);

        this.mockMvc.perform(get("/contas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].numero", is("001")))
                .andExpect(jsonPath("$[0].agencia", is("55")))
                .andExpect(jsonPath("$[0].tipo", is("CONTA_CORRENTE")))
                .andExpect(jsonPath("$[0].produtosFinanceiro[0].tipo", is("CARTAO_CREDITO")))
                .andExpect(jsonPath("$[0].produtosFinanceiro[0].valor", is(2000)))
                .andExpect(jsonPath("$[0].produtosFinanceiro[1].tipo", is("CHEQUE_ESPECIAL")))
                .andExpect(jsonPath("$[0].produtosFinanceiro[1].valor", is(2000)))
                .andExpect(jsonPath("$[1].numero", is("002")))
                .andExpect(jsonPath("$[1].agencia", is("55")))
                .andExpect(jsonPath("$[1].tipo", is("CONTA_EMPRESARIAL")))
                .andExpect(jsonPath("$[1].produtosFinanceiro[0].tipo", is("CARTAO_CREDITO")))
                .andExpect(jsonPath("$[1].produtosFinanceiro[0].valor", is(2000)))
                .andExpect(jsonPath("$[1].produtosFinanceiro[1].tipo", is("CHEQUE_ESPECIAL")))
                .andExpect(jsonPath("$[1].produtosFinanceiro[1].valor", is(2000)));

        verify(service, times(1)).buscarContas();
        verifyNoMoreInteractions(service);
    }

}
