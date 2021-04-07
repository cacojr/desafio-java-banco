package com.api.southsystem.sistema.banco.builder;

import com.api.southsystem.sistema.banco.model.conta.Conta;
import com.api.southsystem.sistema.banco.model.conta.ContaCorrente;
import com.api.southsystem.sistema.banco.model.conta.ContaEmpresarial;
import com.api.southsystem.sistema.banco.model.pessoa.Pessoa;

import java.util.Arrays;
import java.util.List;

public class ContaBuilder {


    public static Conta criarContaCorrente(){
        return new ContaCorrente(PessoaBuilder.criarPessoaFisicaComId());
    }

    public static Conta criarContaEmpresarial(){
        return new ContaEmpresarial(PessoaBuilder.criarPessoaJuridicaComId());
    }


    public static Conta criarContaCorrenteComId(){
        ContaCorrente contaCorrente = new ContaCorrente(PessoaBuilder.criarPessoaFisicaComId());
        contaCorrente.setId(1L);
        return contaCorrente;
    }

    public static Conta criarContaEmpresarialComId(){
        ContaEmpresarial contaEmpresarial = new ContaEmpresarial(PessoaBuilder.criarPessoaJuridicaComId());
        contaEmpresarial.setId(1L);
        return contaEmpresarial;
    }

    public static List<Conta> criarListaDeContas(){
        List<Conta> contas = Arrays.asList(
                criarContaCorrente(),
                criarContaEmpresarial()
        );
        return contas;
    }


    public static List<Conta> criarListaDeContasComId(){
        List<Conta> contas = Arrays.asList(
                criarContaCorrenteComId(),
                criarContaEmpresarialComId()
        );
        return contas;
    }

    public static Conta criarContaCorrtentePassandoPessoa(Pessoa pessoa){
        ContaCorrente contaCorrente = new ContaCorrente(pessoa);
        contaCorrente.setId(1L);
        return contaCorrente;
    }

    public static Conta criarContaEmpresarialPassandoPessoa(Pessoa pessoa){
        ContaEmpresarial contaEmpresarial = new ContaEmpresarial(pessoa);
        contaEmpresarial.setId(2L);
        return contaEmpresarial;
    }

}
