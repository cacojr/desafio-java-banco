package com.api.southsystem.sistema.banco.dto;

import com.api.southsystem.sistema.banco.enums.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PessoaDto {

    private String nome;
    private TipoPessoa tipo;
    private String documento;

}
