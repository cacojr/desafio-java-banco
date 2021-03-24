package com.api.southsystem.sistema.banco.repository;

import com.api.southsystem.sistema.banco.model.conta.Conta;
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
@DisplayName("Teste para o Repository Pessoa")
class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    private Pessoa pessoFisica;

    private Pessoa pessoJuridica;

    @BeforeEach
    void setUp() {

        pessoFisica = new PessoaFisica("Carlos Alberto","84306033287");
        pessoJuridica = new PessoaJuridica("Empresa do Carlos","12463960000177");
    }

    @Test
    @DisplayName("Salva Pessoa Física com sucesso")
    void salvarContaCorrenteSucesso(){

        Pessoa pessoaSalva = pessoaRepository.save(pessoFisica);

        Assertions.assertThat(pessoaSalva)
                .isNotEqualTo(null)
                .isEqualTo(pessoFisica);

        Assertions.assertThat(pessoaSalva.getId())
                .isNotEqualTo(null)
                .isEqualTo(pessoFisica.getId());
    }

    @Test
    @DisplayName("Salva Pessoa Jurídica com sucesso")
    void salvarContaEmpresarialSucesso(){

        Pessoa pessoaSalva = pessoaRepository.save(pessoJuridica);

        Assertions.assertThat(pessoaSalva)
                .isNotEqualTo(null)
                .isEqualTo(pessoJuridica);

        Assertions.assertThat(pessoaSalva.getId())
                .isNotEqualTo(null)
                .isEqualTo(pessoJuridica.getId());
    }

    @Test
    @DisplayName("Carregar Pessoas com sucesso")
    void carregarContasSucesso() {

        Pessoa pessoaFisicaSalva = pessoaRepository.save(pessoFisica);
        Pessoa pessoaJuridicaSalva = pessoaRepository.save(pessoJuridica);

        List<Pessoa> listaPessoas = pessoaRepository.findAll();

        Assertions.assertThat(listaPessoas.size())
                .isGreaterThan(0)
                .isEqualTo(2);

        Assertions.assertThat(listaPessoas)
                .isNotEmpty()
                .isNotNull()
                .contains(pessoaFisicaSalva)
                .contains(pessoaJuridicaSalva);

    }


}