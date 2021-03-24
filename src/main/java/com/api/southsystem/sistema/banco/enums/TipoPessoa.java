package com.api.southsystem.sistema.banco.enums;

public enum TipoPessoa {

    PESSOA("PESSOA"),
    PESSOA_FISICA("FISICA"),
    PESSOA_JURIDICA("JURIDICA");

    private String descricao;

    TipoPessoa(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){return this.descricao;};
    public static String value(TipoPessoa tipo){ return tipo.value(tipo);}
}

