package com.api.southsystem.sistema.banco.repository;

import com.api.southsystem.sistema.banco.model.conta.ContaCorrente;
import com.api.southsystem.sistema.banco.model.conta.ContaEmpresarial;
import com.api.southsystem.sistema.banco.model.pessoa.Pessoa;
import com.api.southsystem.sistema.banco.model.pessoa.PessoaFisica;
import com.api.southsystem.sistema.banco.model.produtofinanceiro.CartaoCredito;
import com.api.southsystem.sistema.banco.model.produtofinanceiro.ChequeEspecial;
import com.api.southsystem.sistema.banco.model.produtofinanceiro.ProdutoFinanceiro;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Testes para o Repository Produto Financeiro")
class ProdutoFinanceiroRepositoryTest {

    @Autowired
    private ProdutoFinanceiroRepository produtoRepository;

    private ProdutoFinanceiro chequeEspecial;

    private ProdutoFinanceiro cartaoCredito;

    @BeforeEach
    void setUp() {

        Pessoa pessoa = new PessoaFisica("Carlos Alberto","84306033287");

        chequeEspecial = new ChequeEspecial(new BigDecimal(1000), new ContaCorrente(pessoa));

        cartaoCredito = new CartaoCredito(new BigDecimal(1000), new ContaCorrente(pessoa));

    }

    @Test
    @DisplayName("Salva Produtos Financeiro com sucesso")
    void salvarProdutosFinanceiroSucesso(){

        List<ProdutoFinanceiro> produtosFinanceiro = new ArrayList<>();
        produtosFinanceiro.add(chequeEspecial);
        produtosFinanceiro.add(cartaoCredito);
        
        produtoRepository.saveAll(produtosFinanceiro);

        Assertions.assertThat(produtosFinanceiro.size())
                .isGreaterThan(0)
                .isEqualTo(2);

        Assertions.assertThat(produtosFinanceiro)
                .isNotEmpty()
                .isNotNull()
                .contains(chequeEspecial)
                .contains(cartaoCredito);
    }

    @Test
    @DisplayName("Carregar Produtos Financeiro com sucesso")
    void carregarContasSucesso() {

        List<ProdutoFinanceiro> produtosFinanceiro = new ArrayList<>();
        produtosFinanceiro.add(chequeEspecial);
        produtosFinanceiro.add(cartaoCredito);

        produtoRepository.saveAll(produtosFinanceiro);

        Assertions.assertThat(produtosFinanceiro.size())
                .isGreaterThan(0)
                .isEqualTo(2);

        Assertions.assertThat(produtosFinanceiro)
                .isNotEmpty()
                .isNotNull()
                .contains(chequeEspecial)
                .contains(cartaoCredito);
    }
}