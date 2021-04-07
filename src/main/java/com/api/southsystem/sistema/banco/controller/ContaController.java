package com.api.southsystem.sistema.banco.controller;

import com.api.southsystem.sistema.banco.dto.ContaDto;
import com.api.southsystem.sistema.banco.services.ContaServices;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaServices contaServices;

    public ContaController(ContaServices contaServices){
        this.contaServices = contaServices;
    }

    @GetMapping
    public ResponseEntity<Collection<ContaDto>> buscarContas(){

        try {

            Collection<ContaDto> contasDto = contaServices.buscarContas();
            return new ResponseEntity<Collection<ContaDto>>(contasDto, HttpStatus.OK);

        } catch (Exception e) {
            return  ResponseEntity.badRequest().build();
        }

    }

}
