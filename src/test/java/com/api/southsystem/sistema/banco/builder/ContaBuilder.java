package com.api.southsystem.sistema.banco.builder;

import com.api.southsystem.sistema.banco.model.conta.Conta;
import com.api.southsystem.sistema.banco.model.conta.ContaCorrente;
import com.api.southsystem.sistema.banco.model.conta.ContaEmpresarial;

import java.util.Arrays;
import java.util.List;

public class ContaBuilder {


    public static Conta criarContaCorrente(){
        return new ContaCorrente(PessoaBuilder.criarPessoaFisica());
    }

    public static Conta criarContaEmpresarial(){
        return new ContaEmpresarial(PessoaBuilder.criarPessoaJuridica());
    }

    public static List<Conta> criarListaDeContas(){
        List<Conta> contas = Arrays.asList(
                criarContaCorrente(),
                criarContaEmpresarial()
        );
        return contas;
    }
}
