package com.api.southsystem.sistema.banco.repository;

import com.api.southsystem.sistema.banco.model.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {}
