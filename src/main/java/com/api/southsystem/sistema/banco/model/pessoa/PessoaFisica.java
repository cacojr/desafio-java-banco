package com.api.southsystem.sistema.banco.model.pessoa;

import com.api.southsystem.sistema.banco.enums.TipoPessoa;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter @Setter
@Entity
public class PessoaFisica extends Pessoa{

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 14)
    private String cpf;

    public PessoaFisica(String nome, String cpf) {
        super(nome, TipoPessoa.PESSOA_FISICA);
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PessoaFisica)) return false;
        PessoaFisica fisica = (PessoaFisica) o;
        return  super.equals(fisica) &&
                getCpf().equals(fisica.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCpf());
    }

    @Override
    public String toString() {
        return "PessoaFisica{" +
                "cpf='" + cpf + '\'' +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", score=" + score +
                ", tipo=" + tipo +
                '}';
    }
}
