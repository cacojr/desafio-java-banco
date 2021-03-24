package com.api.southsystem.sistema.banco.model.pessoa;

import com.api.southsystem.sistema.banco.enums.TipoPessoa;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor
@Entity
public class PessoaJuridica extends Pessoa{

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 18)
    private String cnpj;

    public PessoaJuridica(String nome, String cnpj) {
        super(nome, TipoPessoa.PESSOA_JURIDICA);
        this.cnpj = cnpj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PessoaJuridica)) return false;
        if (!super.equals(o)) return false;
        PessoaJuridica juridica = (PessoaJuridica) o;
        return super.equals(juridica) &&
                getCnpj().equals(juridica.getCnpj());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCnpj());
    }

    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "cnpj='" + cnpj + '\'' +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", score=" + score +
                ", tipo=" + tipo +
                '}';
    }
}