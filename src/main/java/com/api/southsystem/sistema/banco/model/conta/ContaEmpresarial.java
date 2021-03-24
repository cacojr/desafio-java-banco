package com.api.southsystem.sistema.banco.model.conta;

import com.api.southsystem.sistema.banco.enums.TipoConta;
import com.api.southsystem.sistema.banco.model.pessoa.Pessoa;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class ContaEmpresarial extends Conta{

    public ContaEmpresarial(Pessoa pessoa) {
        super(TipoConta.CONTA_EMPRESARIAL,pessoa);
    }
}
