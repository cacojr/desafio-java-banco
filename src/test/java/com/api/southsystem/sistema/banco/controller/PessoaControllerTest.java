package com.api.southsystem.sistema.banco.controller;

import com.api.southsystem.sistema.banco.dto.PessoaDto;
import com.api.southsystem.sistema.banco.enums.TipoPessoa;
import com.api.southsystem.sistema.banco.exceptions.NegocioException;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PessoaController.class)
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PessoaServices service;

    @MockBean
    private ContaServices contaServices;

    @MockBean
    private ProdutoFinanceiroServices produtoFinanceiroServices;


    private List<PessoaDto> pessoasDto;

    @BeforeEach
    void setUp(){

        this.pessoasDto = Arrays.asList(
                new PessoaDto("Carlos", TipoPessoa.PESSOA_FISICA, "84306033287"),
                new PessoaDto("Empresa do Carlos", TipoPessoa.PESSOA_JURIDICA, "84306033287000")
        );

    }

    @Test
    @DisplayName("Carregar todas as pessoas com sucesso")
    void carregarTodasAsPessoas() throws Exception{

        when(service.buscarPessoas()).thenReturn(this.pessoasDto);

        this.mockMvc.perform(get("/pessoas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome", is("Carlos")))
                .andExpect(jsonPath("$[0].tipo", is("PESSOA_FISICA")))
                .andExpect(jsonPath("$[0].documento", is("84306033287")))
                .andExpect(jsonPath("$[1].nome", is("Empresa do Carlos")))
                .andExpect(jsonPath("$[1].tipo", is("PESSOA_JURIDICA")))
                .andExpect(jsonPath("$[1].documento", is("84306033287000")));

        verify(service, times(1)).buscarPessoas();
        verifyNoMoreInteractions(service);
    }

    @Test
    @DisplayName("Criar pessoa com sucesso")
    void criarUmaPessoaComSucesso() throws NegocioException,Exception{

        PessoaDto pessoaDto = new PessoaDto("Carlos", TipoPessoa.PESSOA_FISICA, "84306033287");
        when(service.criarPessoa(any())).thenReturn(pessoaDto);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/pessoas")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(pessoaDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Carlos"))
                .andExpect(jsonPath("$.tipo").value("PESSOA_FISICA"))
                .andExpect(jsonPath("$.documento").value("84306033287"));
    }

}
