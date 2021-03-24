package com.api.southsystem.sistema.banco.repository;

import com.api.southsystem.sistema.banco.model.conta.Conta;
import com.api.southsystem.sistema.banco.model.conta.ContaCorrente;
import com.api.southsystem.sistema.banco.model.conta.ContaEmpresarial;
import com.api.southsystem.sistema.banco.model.pessoa.Pessoa;
import com.api.southsystem.sistema.banco.model.pessoa.PessoaFisica;
import com.api.southsystem.sistema.banco.model.pessoa.PessoaJuridica;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Testes para o Repository Conta")
class ContaRepositoryTest {

    @Autowired
    private ContaRepository contaRepository;

    private Conta contaCorrente;

    private Conta contaEmpresarial;

    @BeforeEach
    void setUp() {

        Pessoa pessoaFisica = new PessoaFisica("Carlos Alberto","84306033287");
        contaCorrente = new ContaCorrente(pessoaFisica);

        Pessoa pessoaJuridica = new PessoaJuridica("Empresa do Carlos","12463960000177");
        contaEmpresarial = new ContaEmpresarial(pessoaJuridica);
    }

    @Test
    @DisplayName("Salva Conta Corrente com sucesso")
    void salvarContaCorrenteSucesso(){

        Conta contaS = contaRepository.save(contaCorrente);

        Assertions.assertThat(contaS)
                .isNotEqualTo(null)
                .isEqualTo(contaCorrente);

        Assertions.assertThat(contaS.getId())
                .isNotEqualTo(null)
                .isEqualTo(contaCorrente.getId());
    }

    @Test
    @DisplayName("Salva Conta Empresarial com sucesso")
    void salvarContaEmpresarialSucesso(){

        Conta contaS = contaRepository.save(contaEmpresarial);

        Assertions.assertThat(contaS)
                .isNotEqualTo(null)
                .isEqualTo(contaEmpresarial);

        Assertions.assertThat(contaS.getId())
                .isNotEqualTo(null)
                .isEqualTo(contaEmpresarial.getId());
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