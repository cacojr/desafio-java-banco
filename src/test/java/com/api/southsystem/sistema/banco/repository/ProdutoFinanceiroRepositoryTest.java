package com.api.southsystem.sistema.banco.repository;

import com.api.southsystem.sistema.banco.builder.ProdutoFinanceiroBuilder;
import com.api.southsystem.sistema.banco.model.produtofinanceiro.ProdutoFinanceiro;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

@DataJpaTest
@DisplayName("Testes para o Repository Produto Financeiro")
class ProdutoFinanceiroRepositoryTest {

    @Autowired
    private ProdutoFinanceiroRepository produtoRepository;

    private List<ProdutoFinanceiro> produtosFinanceiro;

    @BeforeEach
    void setUp() {
        produtosFinanceiro = ProdutoFinanceiroBuilder.criarListaDeProdutosFinanceiro();
    }

    @Test
    @DisplayName("Salva Produtos Financeiro com sucesso")
    void salvarProdutosFinanceiroSucesso(){
        
        List<ProdutoFinanceiro> listaSalva = produtoRepository.saveAll(produtosFinanceiro);

        Assertions.assertThat(listaSalva.size())
                .isGreaterThan(0)
                .isEqualTo(2);

        Assertions.assertThat(listaSalva)
                .isNotEmpty()
                .isNotNull();
//                .contains(chequeEspecial)
//                .contains(cartaoCredito);
    }

    @Test
    @DisplayName("Carregar Produtos Financeiro com sucesso")
    void carregarContasSucesso() {

        produtoRepository.saveAll(produtosFinanceiro);

        Assertions.assertThat(produtosFinanceiro.size())
                .isGreaterThan(0)
                .isEqualTo(2);

        Assertions.assertThat(produtosFinanceiro)
                .isNotEmpty()
                .isNotNull();
    }
}