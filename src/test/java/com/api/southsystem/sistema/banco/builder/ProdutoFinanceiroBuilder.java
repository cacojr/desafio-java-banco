package com.api.southsystem.sistema.banco.builder;

import com.api.southsystem.sistema.banco.model.conta.Conta;
import com.api.southsystem.sistema.banco.model.conta.ContaCorrente;
import com.api.southsystem.sistema.banco.model.conta.ContaEmpresarial;
import com.api.southsystem.sistema.banco.model.produtofinanceiro.CartaoCredito;
import com.api.southsystem.sistema.banco.model.produtofinanceiro.ChequeEspecial;
import com.api.southsystem.sistema.banco.model.produtofinanceiro.ProdutoFinanceiro;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ProdutoFinanceiroBuilder {

    public static ProdutoFinanceiro criarChequeEspecial(){
        return new ChequeEspecial(new BigDecimal(1000),ContaBuilder.criarContaCorrente());
    }

    public static ProdutoFinanceiro criarCartaoCredito(){
        return new CartaoCredito(new BigDecimal(1000),ContaBuilder.criarContaCorrente());
    }

    public static List<ProdutoFinanceiro> criarListaDeProdutosFinanceiro(){
        List<ProdutoFinanceiro> produtos = Arrays.asList(
                criarCartaoCredito(),
                criarChequeEspecial()
        );
        return produtos;
    }
}
