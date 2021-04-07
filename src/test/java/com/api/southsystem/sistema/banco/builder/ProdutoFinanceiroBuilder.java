package com.api.southsystem.sistema.banco.builder;

import com.api.southsystem.sistema.banco.model.conta.Conta;
import com.api.southsystem.sistema.banco.model.conta.ContaCorrente;
import com.api.southsystem.sistema.banco.model.conta.ContaEmpresarial;
import com.api.southsystem.sistema.banco.model.produtofinanceiro.CartaoCredito;
import com.api.southsystem.sistema.banco.model.produtofinanceiro.ChequeEspecial;
import com.api.southsystem.sistema.banco.model.produtofinanceiro.ProdutoFinanceiro;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ProdutoFinanceiroBuilder {

    public static ProdutoFinanceiro criarChequeEspecial(){
        ChequeEspecial chequeEspecial = new ChequeEspecial(new BigDecimal(1000),ContaBuilder.criarContaCorrenteComId());
        chequeEspecial.setId(1L);
        return chequeEspecial;
    }

    public static ProdutoFinanceiro criarCartaoCredito(){
        CartaoCredito cartaoCredito = new CartaoCredito(new BigDecimal(1000),ContaBuilder.criarContaCorrenteComId());
        cartaoCredito.setId(2L);
        return cartaoCredito;
    }

    public static Collection<ProdutoFinanceiro> criarListaDeProdutosFinanceiro(){
        Collection<ProdutoFinanceiro> produtos = Arrays.asList(
                criarCartaoCredito(),
                criarChequeEspecial()
        );
        return produtos;
    }
}
