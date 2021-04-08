package com.api.southsystem.sistema.banco.controller;

import com.api.southsystem.sistema.banco.dto.ContaDto;
import com.api.southsystem.sistema.banco.services.ContaServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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


    @ApiOperation(
            value="Buscar todas as Contas cadastradas",
            response=ResponseEntity.class,
            notes="Essa operação busca todas as contas cadadastradas com seus respectivos produtos finanaceiro")
    @ApiResponses(value= {
            @ApiResponse(
                    code = 200,
                    message = "Retorna um ResponseEntity com a lista de Contas e seus Produtos Financeiro",
                    response = ResponseEntity.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Caso gere alguma Exception retornaremos um ResponseEntity com a mensagem de erro no body",
                    response = ResponseEntity.class
            )
    })
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
