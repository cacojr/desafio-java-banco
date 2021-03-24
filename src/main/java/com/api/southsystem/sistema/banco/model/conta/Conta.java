package com.api.southsystem.sistema.banco.model.conta;

import com.api.southsystem.sistema.banco.enums.TipoConta;
import com.api.southsystem.sistema.banco.model.pessoa.Pessoa;
import com.api.southsystem.sistema.banco.model.produtofinanceiro.ProdutoFinanceiro;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Getter @Setter @NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Conta implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cont_sequence")
    @SequenceGenerator(name="cont_sequence", sequenceName="cont_seq")
    protected Long id;

    @Column(nullable = false)
    protected String numero;

    @Column(nullable = false)
    protected String agencia;

    @Column(nullable = false)
    protected TipoConta tipo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    protected Pessoa pessoa;

    @OneToMany(mappedBy = "conta", fetch = FetchType.LAZY)
    protected List<ProdutoFinanceiro> produtosFinanceiro;

    public Conta(TipoConta tipo, @NotNull Pessoa pessoa){
        this.numero = gerarNumeroConta(pessoa.getId());
        this.agencia = "55";
        this.tipo = tipo;
        this.pessoa = pessoa;
    }

    private String gerarNumeroConta(Long idPessoa){
        return String.format("%06d", idPessoa);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conta)) return false;
        Conta conta = (Conta) o;

        return this.hashCode() == conta.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumero(), getAgencia(), getTipo(), getPessoa());
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", agencia='" + agencia + '\'' +
                ", tipo=" + tipo +
                ", pessoa=" + pessoa +
                ", produtosFinanceiro=" + produtosFinanceiro +
                '}';
    }
}