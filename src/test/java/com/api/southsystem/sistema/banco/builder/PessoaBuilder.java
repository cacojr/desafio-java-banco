package com.api.southsystem.sistema.banco.builder;

import com.api.southsystem.sistema.banco.configs.ModelMapperConfig;
import com.api.southsystem.sistema.banco.dto.PessoaDto;
import com.api.southsystem.sistema.banco.enums.TipoPessoa;
import com.api.southsystem.sistema.banco.model.pessoa.Pessoa;
import com.api.southsystem.sistema.banco.model.pessoa.PessoaFisica;
import com.api.southsystem.sistema.banco.model.pessoa.PessoaJuridica;
import com.api.southsystem.sistema.banco.util.ConversorRepositoryDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class PessoaBuilder {

    @Autowired
    private static ModelMapper modelMapper = new ModelMapperConfig().modelMapper();


    public static Pessoa criarPessoaFisica(){
        return new PessoaFisica("Carlos Alberto", "84306033287");
    }

    public static Pessoa criarPessoaJuridica(){
        return new PessoaFisica("Empresa do Carlos", "8430603387000");
    }

    public static Pessoa criarPessoaFisicaComId(){
        PessoaFisica pessoaFisica = new PessoaFisica("Carlos Alberto", "84306033287");
        pessoaFisica.setId(1L);
        return pessoaFisica;
    }

    public static Pessoa criarPessoaJuridicaComId(){
        PessoaJuridica pessoaJuridica = new PessoaJuridica("Empresa do Carlos", "84306033287000");
        pessoaJuridica.setId(2L);
        return pessoaJuridica;
    }

    public static List<Pessoa> criarListaDePessoas(){
        List<Pessoa> pessoas = Arrays.asList(
                criarPessoaFisica(),
                criarPessoaJuridica()
        );
        return pessoas;
    }

    public static List<Pessoa> criarListaDePessoasComId(){
        List<Pessoa> pessoas = Arrays.asList(
                criarPessoaFisicaComId(),
                criarPessoaJuridicaComId()
        );
        return pessoas;
    }

    public static Pessoa criarPessoaFisicaComScoreComId(int minScore, int maxScore) {
        PessoaFisica pessoaFisica = new PessoaFisica("Carlos Alberto", "84306033287");
        pessoaFisica.setId(1L);
        pessoaFisica.setScore(PessoaBuilder.gerarNumeroRandomicoEntreDoisNumeros(minScore,maxScore));
        return pessoaFisica;
    }

    private static short gerarNumeroRandomicoEntreDoisNumeros(int min, int max) {
        return (short) (new Random().nextInt(max - min + 1) + min);
    }

    public static Pessoa converterPessoaDtoParaPessoa(PessoaDto origem){

        if(origem.getTipo() == TipoPessoa.PESSOA_FISICA)
            return modelMapper.map(origem, PessoaFisica.class);
        else
            return modelMapper.map(origem, PessoaJuridica.class);
    }

    public static PessoaDto converterPessoaParaPessoaDto(Pessoa origem){
        return modelMapper.map(origem, PessoaDto.class);
    }
}
