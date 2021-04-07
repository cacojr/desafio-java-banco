package com.api.southsystem.sistema.banco.services;

import com.api.southsystem.sistema.banco.model.conta.Conta;
import com.api.southsystem.sistema.banco.model.produtofinanceiro.CartaoCredito;
import com.api.southsystem.sistema.banco.model.produtofinanceiro.ChequeEspecial;
import com.api.southsystem.sistema.banco.model.produtofinanceiro.ProdutoFinanceiro;
import com.api.southsystem.sistema.banco.repository.ProdutoFinanceiroRepository;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProdutoFinanceiroServices extends ServicesAbstract<ProdutoFinanceiroRepository,ProdutoFinanceiro,Long>  {


    public Collection<ProdutoFinanceiro> criarProdutos(@NotNull Conta conta){

        List<ProdutoFinanceiro> produtos = this.criarProdutosFinanceiros(conta);
        if(produtos.isEmpty())
            return null;

        return this.salvarTodos(produtos);
    }

    protected List<ProdutoFinanceiro> criarProdutosFinanceiros(@NotNull Conta conta){

        List<ProdutoFinanceiro> lista = new ArrayList<ProdutoFinanceiro>();
        if(conta.getPessoa() != null
                && conta.getPessoa().getScore() >= 0 && conta.getPessoa().getScore() <= 1){
            lista.add(new ChequeEspecial(new BigDecimal(0.00),conta));
        }else if(conta.getPessoa() != null
                && conta.getPessoa().getScore() >= 2 && conta.getPessoa().getScore() <= 5){
            lista.add(new ChequeEspecial(new BigDecimal(1000),conta));
            lista.add(new CartaoCredito(new BigDecimal(200),conta));
        }else if(conta.getPessoa() != null
                && conta.getPessoa().getScore() >= 6 && conta.getPessoa().getScore() <= 8){
            lista.add(new ChequeEspecial(new BigDecimal(2000),conta));
            lista.add(new CartaoCredito(new BigDecimal(2000),conta));
        }else if(conta.getPessoa() != null
                && conta.getPessoa().getScore() == 9 ){
            lista.add(new ChequeEspecial(new BigDecimal(5000),conta));
            lista.add(new CartaoCredito(new BigDecimal(15000),conta));
        }

        return lista;
    }

}
