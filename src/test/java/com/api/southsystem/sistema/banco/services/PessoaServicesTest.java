package com.api.southsystem.sistema.banco.services;

import com.api.southsystem.sistema.banco.builder.PessoaBuilder;
import com.api.southsystem.sistema.banco.configs.ModelMapperConfig;
import com.api.southsystem.sistema.banco.dto.PessoaDto;
import com.api.southsystem.sistema.banco.enums.TipoPessoa;
import com.api.southsystem.sistema.banco.exceptions.NegocioException;
import com.api.southsystem.sistema.banco.model.pessoa.Pessoa;
import com.api.southsystem.sistema.banco.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Testes do Service de Pessoa")
@ExtendWith(MockitoExtension.class)
class PessoaServicesTest {

    @InjectMocks
    private PessoaServices pessoaServices;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ModelMapperConfig modelMapperConfig;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private ContaServices contaServices;

    @BeforeEach
    void setUp()  {

        inicializarService();

        List<Pessoa> pessoas = PessoaBuilder.criarListaDePessoasComId();
        lenient().when(pessoaRepository.findAll()).thenReturn(pessoas);
    }

    @Test
    @DisplayName("Buscar uma lista de todas as pessoas e transformar Objeto Pessoa em Objeto PessoaDto")
    void buscarPessoasDtoComSucesso(){

        Collection<PessoaDto> pessoaDtos = pessoaServices.buscarPessoas();

        assertThat(pessoaDtos.size())
                .isGreaterThan(0)
                .isEqualTo(2);

        assertThat(pessoaDtos.stream().filter(pessoaDto -> !pessoaDto.equals(null)).collect(Collectors.toList()).size())
                .isGreaterThan(0)
                .isEqualTo(2);

        assertThat(pessoaDtos)
                .isNotEmpty()
                .isNotNull();
    }

    @Test
    @DisplayName("Criação de uma pessoa física Através do DTO: PessoaDto")
    void criarPessoaFisicaComSucesso() throws Exception {

        Pessoa pessoaFisicaComId = this.criaPessoaFisica();

        PessoaDto pessoaDto = PessoaBuilder.converterPessoaParaPessoaDto(pessoaFisicaComId);
        pessoaServices.criarPessoa(pessoaDto);

        assertThat(pessoaDto)
                .isNotNull();

        assertThat(pessoaDto.getTipo())
                .isEqualTo(TipoPessoa.PESSOA_FISICA);

        assertThat(pessoaDto.getDocumento().length())
                .isEqualTo(11);
    }

    @Test
    @DisplayName("Gerando um NegocioException ao tentar criar uma Pessoa Fisica")
    void gerandoExceptionAoCriarPessoaFisica() throws Exception{

        PessoaDto pessoaDto = PessoaBuilder.converterPessoaParaPessoaDto(this.criaPessoaFisica());
        pessoaDto.setDocumento("8430603387");

        try {
            pessoaServices.criarPessoa(pessoaDto);
            fail("Falha ao executar o teste - um NegocioException deve ser lançado");
        } catch (NegocioException e) {
            assertThat(e)
            .isInstanceOf(NegocioException.class);
        }


    }

    @Test
    @DisplayName("Criação de uma pessoa jurídica Através do DTO: PessoaDto ")
    void criarPessoaJuridicaComSucesso() throws Exception {

        Pessoa pessoaFisicaComId = this.criaPessoaJuridica();

        PessoaDto pessoaDto = PessoaBuilder.converterPessoaParaPessoaDto(pessoaFisicaComId);
        pessoaServices.criarPessoa(pessoaDto);

        assertThat(pessoaDto)
                .isNotNull();

        assertThat(pessoaDto.getTipo())
                .isEqualTo(TipoPessoa.PESSOA_JURIDICA);

        assertThat(pessoaDto.getDocumento().length())
                .isEqualTo(14);
    }

    @Test
    @DisplayName("Gerando um NegocioException ao tentar criar uma Pessoa Jurídica")
    void gerandoExceptionAoCriarPessoaJuridica() throws Exception{

        PessoaDto pessoaDto = PessoaBuilder.converterPessoaParaPessoaDto(this.criaPessoaJuridica());
        pessoaDto.setDocumento("8430603328700");

        try {
            pessoaServices.criarPessoa(pessoaDto);
            fail("Falha ao executar o teste - um NegocioException deve ser lançado");
        } catch (NegocioException e) {
            assertThat(e)
                    .isInstanceOf(NegocioException.class);
        }


    }

    void inicializarService(){

        modelMapperConfig = new ModelMapperConfig();
        modelMapper = modelMapperConfig.modelMapper();

        pessoaServices = new PessoaServices(contaServices,modelMapper);
        pessoaServices.repository = pessoaRepository;

    }

    Pessoa criaPessoaFisica(){

        Pessoa pessoaFisicaComId = PessoaBuilder.criarPessoaFisicaComId();
        criarMocks(pessoaFisicaComId);

        return pessoaFisicaComId;
    }

    Pessoa criaPessoaJuridica(){

        Pessoa pessoaJuridicaComId = PessoaBuilder.criarPessoaJuridicaComId();
        this.criarMocks(pessoaJuridicaComId);

        return pessoaJuridicaComId;
    }

    void criarMocks(Pessoa pessoa){

        lenient().when(pessoaRepository.findById(pessoa.getId())).
                thenReturn(Optional.of(pessoa));

        lenient().when(pessoaRepository.save(pessoa)).
                thenReturn(pessoa);
    }
}