package com.api.southsystem.sistema.banco.repository;

import com.api.southsystem.sistema.banco.builder.ContaBuilder;
import com.api.southsystem.sistema.banco.model.conta.Conta;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@DisplayName("Testes para o Repository Conta")
class ContaRepositoryTest {

    @Autowired
    private ContaRepository contaRepository;

    private Conta contaCorrente;

    private Conta contaEmpresarial;

    @BeforeEach
    void setUp() {

        contaCorrente = ContaBuilder.criarContaCorrenteComId();
        contaEmpresarial = ContaBuilder.criarContaEmpresarialComId();
    }

    @Test
    @DisplayName("Salva Conta Corrente com sucesso")
    void salvarContaCorrenteSucesso(){

        Conta contaS = contaRepository.save(contaCorrente);

        Assertions.assertThat(contaS)
                .isNotEqualTo(null)
                .isEqualTo(contaCorrente);
    }

    @Test
    @DisplayName("Salva Conta Empresarial com sucesso")
    void salvarContaEmpresarialSucesso(){

        Conta contaS = contaRepository.save(contaEmpresarial);

        Assertions.assertThat(contaS)
                .isNotEqualTo(null)
                .isEqualTo(contaEmpresarial);
    }

    @Test
    @DisplayName("Carregar Contas com sucesso")
    void carregarContasSucesso() {

        Conta contaEmpresa = contaRepository.save(contaEmpresarial);
        Conta contaFisica = contaRepository.save(contaCorrente);

        List<Conta> listaContas = contaRepository.findAll();

        Assertions.assertThat(listaContas.size())
                .isGreaterThan(0)
                .isEqualTo(2);

        Assertions.assertThat(listaContas)
                .isNotEmpty()
                .isNotNull()
                .contains(contaEmpresa)
                .contains(contaFisica);
    }

}