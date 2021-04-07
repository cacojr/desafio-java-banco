package com.api.southsystem.sistema.banco.controller;

import com.api.southsystem.sistema.banco.dto.PessoaDto;
import com.api.southsystem.sistema.banco.exceptions.NegocioException;
import com.api.southsystem.sistema.banco.services.PessoaServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping({"pessoas"})
public class PessoaController {

    private PessoaServices service;

    PessoaController(PessoaServices service){
        this.service = service;

    }

    @GetMapping
    @ResponseBody
    public ResponseEntity buscarPessoas(){

        try {
            Collection<PessoaDto> pessoasDto = service.buscarPessoas();
            return new ResponseEntity<Collection<PessoaDto>>(pessoasDto, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.badRequest().build();
        }

    }

    @PostMapping
    @ResponseBody
    public ResponseEntity criarPessoa(@RequestBody PessoaDto pessoaDto){

        try {
            PessoaDto pessoaDtoReponse =  service.criarPessoa(pessoaDto);

            if(pessoaDto.equals(null))
                ResponseEntity.notFound().build();

            return new ResponseEntity<>(pessoaDtoReponse,HttpStatus.CREATED);

        }catch (NegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro Interno");
        }

    }
}
