package com.api.southsystem.sistema.banco.enums;

public enum TipoConta {

    SEM_CLASSIFICACAO(0,"SEM CLASSIFICAÇÃO"),
    CONTA_CORRENTE(1,"CORRENTE"),
    CONTA_EMPRESARIAL(2,"EMPRESARIAL");

    private Integer codigo;
    private String descricao;

    TipoConta(Integer codigo,String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    };

    public Integer getCodigo(){
        return this.codigo;
    };
}