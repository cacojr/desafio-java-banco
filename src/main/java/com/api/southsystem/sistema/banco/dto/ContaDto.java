package com.api.southsystem.sistema.banco.dto;

import com.api.southsystem.sistema.banco.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ContaDto {

    protected String numero;
    protected String agencia;
    protected TipoConta tipo;
    protected List<ProdutoFinanceiroDto> produtosFinanceiro;

}
