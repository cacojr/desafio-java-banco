package com.api.southsystem.sistema.banco.model.pessoa;


import com.api.southsystem.sistema.banco.enums.TipoPessoa;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Getter @Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pessoa_sequence")
    @SequenceGenerator(name="pessoa_sequence", sequenceName="pes_seq")
    @Column(unique=true, nullable=false)
    protected Long id;

    @Column(nullable=false, length=255)
    protected String nome;

    @Column(nullable = false)
    protected short score;

    @Column(nullable = false)
    protected TipoPessoa tipo;


    public Pessoa(String nome,TipoPessoa tipo){
        this.nome = nome;
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;
        Pessoa pessoa = (Pessoa) o;
        return getNome().equals(pessoa.getNome()) &&
                getTipo() == pessoa.getTipo();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getTipo());
    }


}