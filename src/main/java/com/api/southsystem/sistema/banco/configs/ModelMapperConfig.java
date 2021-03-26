package com.api.southsystem.sistema.banco.configs;

import com.api.southsystem.sistema.banco.dto.PessoaDto;
import com.api.southsystem.sistema.banco.model.pessoa.PessoaFisica;
import com.api.southsystem.sistema.banco.model.pessoa.PessoaJuridica;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(PessoaFisica.class, PessoaDto.class)
                .<String>addMapping(src->src.getCpf(),(dest,value)->dest.setDocumento(value));

        modelMapper.createTypeMap(PessoaDto.class,PessoaFisica.class)
                .<String>addMapping(src->src.getDocumento(),(dest,value)->dest.setCpf(value));

        modelMapper.createTypeMap(PessoaJuridica.class, PessoaDto.class)
                .<String>addMapping(src->src.getCnpj(),(dest,value)->dest.setDocumento(value));

        modelMapper.createTypeMap(PessoaDto.class,PessoaJuridica.class)
                .<String>addMapping(src->src.getDocumento(),(dest,value)->dest.setCnpj(value));

        return modelMapper;
    }
}
