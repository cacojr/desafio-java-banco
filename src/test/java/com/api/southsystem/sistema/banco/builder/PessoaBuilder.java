package com.api.southsystem.sistema.banco.builder;

import com.api.southsystem.sistema.banco.model.pessoa.Pessoa;
import com.api.southsystem.sistema.banco.model.pessoa.PessoaFisica;

import java.util.Arrays;
import java.util.List;

public class PessoaBuilder {

    public static Pessoa criarPessoaFisica(){
        return new PessoaFisica("Carlos Albert", "8430603387");
    }

    public static Pessoa criarPessoaJuridica(){
        return new PessoaFisica("Empresa do Carlos", "8430603387000");
    }

    public static List<Pessoa> criarListaDePessoas(){
        List<Pessoa> pessoas = Arrays.asList(
                criarPessoaFisica(),
                criarPessoaJuridica()
        );
        return pessoas;
    }
}
