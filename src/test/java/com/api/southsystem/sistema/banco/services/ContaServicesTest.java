package com.api.southsystem.sistema.banco.services;

import com.api.southsystem.sistema.banco.builder.ContaBuilder;
import com.api.southsystem.sistema.banco.builder.PessoaBuilder;
import com.api.southsystem.sistema.banco.configs.ModelMapperConfig;
import com.api.southsystem.sistema.banco.dto.ContaDto;
import com.api.southsystem.sistema.banco.enums.TipoConta;
import com.api.southsystem.sistema.banco.model.conta.Conta;
import com.api.southsystem.sistema.banco.model.pessoa.Pessoa;
import com.api.southsystem.sistema.banco.repository.ContaRepository;
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
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Testes do Service de Conta")
@ExtendWith(MockitoExtension.class)
class ContaServicesTest {

    @InjectMocks
    private ContaServices contaServices;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ModelMapperConfig modelMapperConfig;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private ProdutoFinanceiroServices produtoFinanceiroServices;

    @BeforeEach
    void setUp() {

        inicializarService();

        List<Conta> contas = ContaBuilder.criarListaDeContasComId();
        lenient().when(contaRepository.findAll()).thenReturn(contas);
    }


    @Test
    @DisplayName("Salvar produtos financeiro com sucesso")
    void buscarContasDtoComSucesso(){
        Collection<ContaDto> contasDtos = contaServices.buscarContas();

        assertThat(contasDtos.size())
                .isGreaterThan(0)
                .isEqualTo(2);

        assertThat(contasDtos.stream().filter(contaDto -> !contaDto.equals(null)).collect(Collectors.toList()).size())
                .isGreaterThan(0)
                .isEqualTo(2);

        assertThat(contasDtos)
                .isNotEmpty()
                .isNotNull();
    }

    @Test
    @DisplayName("Criação de uma Conta Corrente a partir de uma Pessoa Física cadastrada")
    void criarContaCorrenteComSucesso() throws Exception {

        Pessoa pessoaFisicaComId = PessoaBuilder.criarPessoaFisicaComId();
        Conta conta = this.criaContaCorrente(pessoaFisicaComId);

        contaServices.criarConta(pessoaFisicaComId);
        verify(contaRepository).save(any(Conta.class));
        assertThat(conta)
                .isNotNull();
        assertThat(conta.getTipo())
                .isEqualTo(TipoConta.CONTA_CORRENTE);
    }

    @Test
    @DisplayName("Criação de uma Conta Empresarial a partir de uma Pessoa Juridica cadastrada")
    void criarContaEmpresarialComSucesso() throws Exception {

        Pessoa pessoaJuridicaComId = PessoaBuilder.criarPessoaJuridicaComId();
        Conta conta = this.criaContaEmpresarial(pessoaJuridicaComId);

        contaServices.criarConta(pessoaJuridicaComId);
        verify(contaRepository).save(any(Conta.class));
        assertThat(conta)
                .isNotNull();
        assertThat(conta.getTipo())
                .isEqualTo(TipoConta.CONTA_EMPRESARIAL);
    }


    void inicializarService(){

        modelMapperConfig = new ModelMapperConfig();
        modelMapper = modelMapperConfig.modelMapper();

        contaServices = new ContaServices(produtoFinanceiroServices,modelMapper);
        contaServices.repository = contaRepository;
    }


    Conta criaContaCorrente(Pessoa pessoa){

        Conta contaCorrente = ContaBuilder.criarContaCorrtentePassandoPessoa(pessoa);
        criarMocks(contaCorrente);

        return contaCorrente;
    }

    Conta criaContaEmpresarial(Pessoa pessoa){

        Conta contaEmpresarial = ContaBuilder.criarContaEmpresarialPassandoPessoa(pessoa);
        criarMocks(contaEmpresarial);

        return contaEmpresarial;
    }

    void criarMocks(Conta conta){

        lenient().when(contaRepository.save(conta)).
                thenReturn(conta);
    }
}