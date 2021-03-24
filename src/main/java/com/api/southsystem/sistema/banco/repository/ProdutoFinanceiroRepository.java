package com.api.southsystem.sistema.banco.repository;

import com.api.southsystem.sistema.banco.model.produtofinanceiro.ProdutoFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoFinanceiroRepository extends JpaRepository<ProdutoFinanceiro, Long> {}
