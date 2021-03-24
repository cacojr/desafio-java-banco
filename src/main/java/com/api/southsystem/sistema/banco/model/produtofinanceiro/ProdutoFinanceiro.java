package com.api.southsystem.sistema.banco.model.produtofinanceiro;

import com.api.southsystem.sistema.banco.enums.TipoProdutoFinanceiro;
import com.api.southsystem.sistema.banco.model.conta.Conta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ProdutoFinanceiro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_sequence")
    @SequenceGenerator(name = "prod_sequence",sequenceName = "prod_seq")
    @Column(unique = true,nullable = false)
    protected Long id;

    @Column(nullable = false)
    protected TipoProdutoFinanceiro tipo;

    @Column(nullable = false)
    protected BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id", referencedColumnName = "id")
    protected Conta conta;

    public ProdutoFinanceiro(TipoProdutoFinanceiro tipo, BigDecimal valor, Conta conta) {
        this.tipo = tipo;
        this.valor = valor;
        this.conta = conta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoFinanceiro)) return false;
        ProdutoFinanceiro produtoFinanceiro = (ProdutoFinanceiro) o;
        return getTipo() == produtoFinanceiro.getTipo() &&
                getValor().equals(produtoFinanceiro.getValor()) &&
                getConta().equals(produtoFinanceiro.getConta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTipo(), getValor(), getConta());
    }

    @Override
    public String toString() {
        return "ProdutoFinanceiro{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", valor=" + valor +
                ", conta=" + conta +
                '}';
    }
}