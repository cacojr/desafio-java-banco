package com.api.southsystem.sistema.banco.util;

public interface ConversorRepositoryDTO<S, D> {

    D conversorDtoEntidade(S origem);

    S conversorEntidadeDto(D destino);
}
