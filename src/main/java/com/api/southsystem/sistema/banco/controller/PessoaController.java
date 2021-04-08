package com.api.southsystem.sistema.banco.controller;

import com.api.southsystem.sistema.banco.dto.PessoaDto;
import com.api.southsystem.sistema.banco.exceptions.NegocioException;
import com.api.southsystem.sistema.banco.services.PessoaServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(
            value="Buscar todas as Pessoas Cadastradas",
            response=ResponseEntity.class,
            notes="Essa operação busca todas as pessoas cadastradas")
    @ApiResponses(value= {
            @ApiResponse(
                    code = 200,
                    message = "Retorna um ResponseEntity com a lista de Pessoas",
                    response = ResponseEntity.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Caso gere alguma Exception retornaremos um ResponseEntity com a mensagem de erro no body",
                    response = ResponseEntity.class
            )
    })
    @GetMapping
    @ResponseBody
    public ResponseEntity buscarPessoas(){

        try {
            Collection<PessoaDto> pessoasDto = service.buscarPessoas();
            return new ResponseEntity<Collection<PessoaDto>>(pessoasDto, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @ApiOperation(
            value="Cadastrar uma nova pessoa",
            response=ResponseEntity.class,
            notes="Essa operação salva um novo registro com as informações de pessoa e cria uma nova conta com seus respectivos produtos financeiros associados de acordo com o Score da pessoa.")
    @ApiResponses(value= {
            @ApiResponse(
                    code=201,
                    message="Retorna um ResponseEntity com a Pessoa Cadastrada",
                    response=ResponseEntity.class
            ),
            @ApiResponse(
                    code=400,
                    message="Caso tenhamos algum erro de negócio vamos retornar um ResponseEntity com  mensagem específica do erro",
                    response=ResponseEntity.class
            ),
            @ApiResponse(
                    code=404,
                    message="Caso o recurso não tenha sido encontrado retornaremos um ResponseEntity",
                    response=ResponseEntity.class
            ),
            @ApiResponse(
                    code=500,
                    message="Caso tenhamos algum erro vamos retornar um ResponseEntity com  mensagem: Erro Interno",
                    response=ResponseEntity.class
            )

    })
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
            return new ResponseEntity<>("Erro Interno",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
