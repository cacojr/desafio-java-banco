package com.api.southsystem.sistema.banco.services;

import com.api.southsystem.sistema.banco.dto.PessoaDto;
import com.api.southsystem.sistema.banco.enums.TipoPessoa;
import com.api.southsystem.sistema.banco.exceptions.NegocioException;
import com.api.southsystem.sistema.banco.model.pessoa.Pessoa;
import com.api.southsystem.sistema.banco.model.pessoa.PessoaFisica;
import com.api.southsystem.sistema.banco.model.pessoa.PessoaJuridica;
import com.api.southsystem.sistema.banco.repository.PessoaRepository;
import com.api.southsystem.sistema.banco.util.ConversorRepositoryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PessoaServices extends ServicesAbstract<PessoaRepository,Pessoa,Long> implements ConversorRepositoryDTO<PessoaDto,Pessoa> {

    private final ContaServices contaServices;
    private final ModelMapper modelMapper;

    PessoaServices(ContaServices contaServices,ModelMapper modelMapper){
        this.contaServices = contaServices;
        this.modelMapper = modelMapper;
    }

    public Collection<PessoaDto> buscarPessoas(){
        return this.buscarTodos().stream()
                .map(this::conversorEntidadeDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<PessoaDto> criarPessoa(PessoaDto pessoaDto) throws   Exception {

        if(pessoaDto.getNome().isEmpty())
            throw  new NegocioException("Nome obrigatório");

        Pessoa pessoa = this.conversorDtoEntidade(pessoaDto);

        if(pessoa == null)
            throw new NegocioException("Não foi possível criar a Pessoa");

        if(isPessoaFisica(pessoaDto.getDocumento()))
            pessoa.setTipo(TipoPessoa.PESSOA_FISICA);
        else if(isPessoaJuridica(pessoaDto.getDocumento()))
            pessoa.setTipo(TipoPessoa.PESSOA_FISICA);
        else
            throw new NegocioException("Documento Inválido");

        pessoa.setScore(this.gerarScore());

        this.salvar(pessoa);

        contaServices.criarConta(pessoa);

        return this.buscarPorId(pessoa.getId())
                .map(p -> this.conversorEntidadeDto(p));
    }

    private boolean isPessoaFisica(String numeroDocumento){
        return numeroDocumento != null && this.removeCaracterNaoNumerico(numeroDocumento).length() == 11;
    }

    private boolean isPessoaJuridica(String numeroDocumento){
        return numeroDocumento != null && this.removeCaracterNaoNumerico(numeroDocumento).length() == 14;
    }

    private String removeCaracterNaoNumerico(String valor){
        return valor != null ? valor.replaceAll("[^0-9]+","") : "";
    }

    private short gerarScore(){
        return (short) new Random().nextInt(9);
    }

    @Override
    public Pessoa conversorDtoEntidade(PessoaDto origem) {
        if(origem.getTipo() == TipoPessoa.PESSOA_FISICA)
            return modelMapper.map(origem, PessoaFisica.class);
        else
            return modelMapper.map(origem, PessoaJuridica.class);
    }

    @Override
    public PessoaDto conversorEntidadeDto(Pessoa origem) {
        return modelMapper.map(origem, PessoaDto.class);
    }
}
