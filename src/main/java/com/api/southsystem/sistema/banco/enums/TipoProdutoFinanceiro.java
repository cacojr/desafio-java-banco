package com.api.southsystem.sistema.banco.enums;

public enum  TipoProdutoFinanceiro {

    PRODUTO_FINANCEIRO(0,"PRODUTO FINANCEIRO"),
    CHEQUE_ESPECIAL(1,"LIMITE CHEQUE ESPECIAL"),
    CARTAO_CREDITO(2,"CARTÃO DE CRÉDITO");

    private String descricao;
    private Integer codigo;

    TipoProdutoFinanceiro(Integer codigo,String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public Integer getCodigo(){ return this.codigo; }
}

