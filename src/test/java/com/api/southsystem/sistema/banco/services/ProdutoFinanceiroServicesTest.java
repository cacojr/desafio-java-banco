package com.api.southsystem.sistema.banco.services;

import com.api.southsystem.sistema.banco.builder.ContaBuilder;
import com.api.southsystem.sistema.banco.builder.PessoaBuilder;
import com.api.southsystem.sistema.banco.builder.ProdutoFinanceiroBuilder;
import com.api.southsystem.sistema.banco.enums.TipoProdutoFinanceiro;
import com.api.southsystem.sistema.banco.model.conta.Conta;
import com.api.southsystem.sistema.banco.model.produtofinanceiro.ProdutoFinanceiro;
import com.api.southsystem.sistema.banco.repository.ProdutoFinanceiroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Testes do Service de Produto Financeiro")
@ExtendWith(MockitoExtension.class)
class ProdutoFinanceiroServicesTest {

    @Spy
    @InjectMocks
    private ProdutoFinanceiroServices produtoFinanceiroServices;

    @Mock
    private ProdutoFinanceiroRepository produtoFinanceiroRepository;

    @BeforeEach
    void setUp() {
        produtoFinanceiroServices.repository = produtoFinanceiroRepository;
    }

    @Test
    @DisplayName("Criar Produtos Financeiros")
    void criarProduto(){

        Collection<ProdutoFinanceiro> lista = ProdutoFinanceiroBuilder.criarListaDeProdutosFinanceiro();
        Conta conta = ContaBuilder.criarContaCorrenteComId();
        when(produtoFinanceiroRepository.saveAll(lista)).thenReturn((List<ProdutoFinanceiro>) lista);
        when(produtoFinanceiroServices.criarProdutosFinanceiros(conta)).thenReturn((List<ProdutoFinanceiro>) lista);

        Collection<ProdutoFinanceiro> listaInserida = produtoFinanceiroServices.criarProdutos(conta);

        assertThat(listaInserida)
                .isNotEmpty()
                .isNotNull();

        assertThat(listaInserida.size())
                .isGreaterThan(0);

        assertThat(listaInserida.stream().filter(produtoFinanceiro -> !produtoFinanceiro.equals(null)).collect(Collectors.toList()).size())
                .isGreaterThan(0)
                .isBetween(1,2);

    }

    @Test
    @DisplayName("Criar Produto Financeiro com Pessoa entre o Score 0 e 1 com sucesso")
    void criarProdutosFinanceirosComPessoaComScoreEntre0e1(){

        Conta conta = ContaBuilder.criarContaCorrtentePassandoPessoa(PessoaBuilder.criarPessoaFisicaComScoreComId(0,1));

        Collection<ProdutoFinanceiro> listaProdutosGerada = produtoFinanceiroServices.criarProdutosFinanceiros(conta);

        assertThat(listaProdutosGerada)
                .isNotEmpty()
                .isNotNull();

        assertThat(listaProdutosGerada.size())
                .isGreaterThan(0);

        assertThat(listaProdutosGerada.stream().filter(
                        produtoFinanceiro ->
                        produtoFinanceiro.getTipo().equals(TipoProdutoFinanceiro.CHEQUE_ESPECIAL)
                                && produtoFinanceiro.getValor().equals(new BigDecimal(0.00))
                )
                .collect(Collectors.toList()).size())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("Criar Produto Financeiro com Pessoa entre o Score 2 e 5 com sucesso")
    void criarProdutosFinanceirosComPessoaComScoreEntre2e5(){

        Conta conta = ContaBuilder.criarContaCorrtentePassandoPessoa(PessoaBuilder.criarPessoaFisicaComScoreComId(2,5));

        Collection<ProdutoFinanceiro> listaProdutosGerada = produtoFinanceiroServices.criarProdutosFinanceiros(conta);

        assertThat(listaProdutosGerada)
                .isNotEmpty()
                .isNotNull();

        assertThat(listaProdutosGerada.size())
                .isGreaterThan(0);

        assertThat(listaProdutosGerada.stream().filter(
                produtoFinanceiro -> {

                    if(produtoFinanceiro.getTipo().equals(TipoProdutoFinanceiro.CHEQUE_ESPECIAL) && produtoFinanceiro.getValor().equals(new BigDecimal(1000)))
                        return true;
                    else if(produtoFinanceiro.getTipo().equals(TipoProdutoFinanceiro.CARTAO_CREDITO) && produtoFinanceiro.getValor().equals(new BigDecimal(200)))
                        return true;

                    return false;
                })
                .collect(Collectors.toList()).size())
                .isEqualTo(2);
    }

    @Test
    @DisplayName("Criar Produto Financeiro com Pessoa entre o Score 6 e 8 com sucesso")
    void criarProdutosFinanceirosComPessoaComScoreEntre6e8(){

        Conta conta = ContaBuilder.criarContaCorrtentePassandoPessoa(PessoaBuilder.criarPessoaFisicaComScoreComId(6,8));

        Collection<ProdutoFinanceiro> listaProdutosGerada = produtoFinanceiroServices.criarProdutosFinanceiros(conta);

        assertThat(listaProdutosGerada)
                .isNotEmpty()
                .isNotNull();

        assertThat(listaProdutosGerada.size())
                .isGreaterThan(0);

        assertThat(listaProdutosGerada.stream().filter(
                produtoFinanceiro -> {

                    if(produtoFinanceiro.getTipo().equals(TipoProdutoFinanceiro.CHEQUE_ESPECIAL) && produtoFinanceiro.getValor().equals(new BigDecimal(2000)))
                        return true;
                    else if(produtoFinanceiro.getTipo().equals(TipoProdutoFinanceiro.CARTAO_CREDITO) && produtoFinanceiro.getValor().equals(new BigDecimal(2000)))
                        return true;

                    return false;
                })
                .collect(Collectors.toList()).size())
                .isEqualTo(2);
    }

    @Test
    @DisplayName("Criar Produtos Financeiro com Pessoa com o Score igual a 9 com sucesso")
    void criarProdutosFinanceirosComPessoaComScoreIgual9(){

        Conta conta = ContaBuilder.criarContaCorrtentePassandoPessoa(PessoaBuilder.criarPessoaFisicaComScoreComId(9,9));

        Collection<ProdutoFinanceiro> listaProdutosGerada = produtoFinanceiroServices.criarProdutosFinanceiros(conta);

        assertThat(listaProdutosGerada)
                .isNotEmpty()
                .isNotNull();

        assertThat(listaProdutosGerada.size())
                .isGreaterThan(0);

        assertThat(listaProdutosGerada.stream().filter(
                produtoFinanceiro -> {

                    if(produtoFinanceiro.getTipo().equals(TipoProdutoFinanceiro.CHEQUE_ESPECIAL) && produtoFinanceiro.getValor().equals(new BigDecimal(5000)))
                        return true;
                    else if(produtoFinanceiro.getTipo().equals(TipoProdutoFinanceiro.CARTAO_CREDITO) && produtoFinanceiro.getValor().equals(new BigDecimal(15000)))
                        return true;

                    return false;
                })
                .collect(Collectors.toList()).size())
                .isEqualTo(2);
    }

    @Test
    @DisplayName("Não criar Produtos Financeiro com Pessoa com o Score maior que o intervalo de 0 a 9 com sucesso")
    void naoCriarProdutosFinanceirosComPessoaComScoreMaiorQueOIntervaloDe0a9() {

        Conta conta = ContaBuilder.criarContaCorrtentePassandoPessoa(PessoaBuilder.criarPessoaFisicaComScoreComId(10, 11));

        Collection<ProdutoFinanceiro> listaProdutosGerada = produtoFinanceiroServices.criarProdutosFinanceiros(conta);

        assertThat(listaProdutosGerada)
                .isEmpty();

        assertThat(listaProdutosGerada.size())
                .isEqualTo(0);
    }
}