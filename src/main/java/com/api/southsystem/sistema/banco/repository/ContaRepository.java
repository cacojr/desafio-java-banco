package com.api.southsystem.sistema.banco.repository;

import com.api.southsystem.sistema.banco.model.conta.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta,Long> {}
