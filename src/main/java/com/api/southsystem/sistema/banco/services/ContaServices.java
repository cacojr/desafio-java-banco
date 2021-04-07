package com.api.southsystem.sistema.banco.services;


import com.api.southsystem.sistema.banco.dto.ContaDto;
import com.api.southsystem.sistema.banco.enums.TipoConta;
import com.api.southsystem.sistema.banco.enums.TipoPessoa;
import com.api.southsystem.sistema.banco.model.conta.Conta;
import com.api.southsystem.sistema.banco.model.conta.ContaCorrente;
import com.api.southsystem.sistema.banco.model.conta.ContaEmpresarial;
import com.api.southsystem.sistema.banco.model.pessoa.Pessoa;
import com.api.southsystem.sistema.banco.repository.ContaRepository;
import com.api.southsystem.sistema.banco.util.ConversorRepositoryDTO;
import com.sun.istack.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaServices extends ServicesAbstract<ContaRepository, Conta,Long>  implements ConversorRepositoryDTO<ContaDto, Conta> {

    private final ProdutoFinanceiroServices produtoFinanceiroServices;

    private final ModelMapper modelMapper;

    ContaServices(ProdutoFinanceiroServices produtoFinanceiroServices,ModelMapper modelMapper){
        this.produtoFinanceiroServices = produtoFinanceiroServices;
        this.modelMapper = modelMapper;
    }



    public Collection<ContaDto> buscarContas() {
        List<ContaDto> contas = this.buscarTodos()
                .stream()
                .map(this::conversorEntidadeDto)
                .collect(Collectors.toList());
        return contas;
    }

    public void criarConta(@NotNull Pessoa p) throws Exception{

        Conta conta = (p.getTipo().equals(TipoPessoa.PESSOA_FISICA) ? new ContaCorrente(p) : new ContaEmpresarial(p));
        conta = this.salvar(conta);

        produtoFinanceiroServices.criarProdutos(conta);
    }

    @Override
    public Conta conversorDtoEntidade(ContaDto origem) {
        if(origem.getTipo() == TipoConta.CONTA_CORRENTE)
            return modelMapper.map(origem, ContaCorrente.class);
        else
            return modelMapper.map(origem, ContaEmpresarial.class);
    }

    @Override
    public ContaDto conversorEntidadeDto(Conta destino) {
        return modelMapper.map(destino, ContaDto.class);
    }
}
