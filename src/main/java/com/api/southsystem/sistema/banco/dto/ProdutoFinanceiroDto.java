package com.api.southsystem.sistema.banco.dto;

import com.api.southsystem.sistema.banco.enums.TipoProdutoFinanceiro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProdutoFinanceiroDto {

    protected TipoProdutoFinanceiro tipo;
    protected BigDecimal valor;
}

