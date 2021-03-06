package com.api.southsystem.sistema.banco.model.produtofinanceiro;


import com.api.southsystem.sistema.banco.enums.TipoProdutoFinanceiro;
import com.api.southsystem.sistema.banco.model.conta.Conta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter @Setter
@Entity
public class CartaoCredito extends  ProdutoFinanceiro{

    public CartaoCredito(BigDecimal valor, Conta conta){
        super(TipoProdutoFinanceiro.CARTAO_CREDITO,valor,conta);
    }
}
