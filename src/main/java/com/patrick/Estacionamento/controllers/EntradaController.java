package com.patrick.Estacionamento.controllers;

import com.patrick.Estacionamento.entities.Veiculo;
import com.patrick.Estacionamento.services.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Entrada de Veículos", description = "Operações relacionadas ao registro de entrada de veículos no estacionamento")
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    @PostMapping("/entrada")
    @Operation(
            summary = "Registrar entrada de veículo",
            description = "Endpoint para registrar a entrada de um veículo no estacionamento. Requer a placa do veículo.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do veículo para registro",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Veiculo.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo básico",
                                            value = "{\"placa\": \"ABC-1234\"}",
                                            summary = "Registro simples"
                                    )
                            }
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Entrada registrada com sucesso",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Veículo com placa ABC-1234 registrado com sucesso!"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos fornecidos",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "A placa do veículo é obrigatória."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno no servidor",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Erro ao registrar a entrada: [mensagem de erro]"
                            )
                    )
            )
    })
    public ResponseEntity<String> registrarEntrada(
            @Parameter(
                    description = "Objeto contendo os dados do veículo",
                    required = true,
                    schema = @Schema(implementation = Veiculo.class))
            @RequestBody Veiculo veiculo) {

        if (veiculo == null || veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A placa do veículo é obrigatória.");
        }

        try {
            entradaService.registrarEntrada(veiculo, LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.CREATED).body("Veículo com placa " + veiculo.getPlaca() + " registrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao registrar a entrada: " + e.getMessage());
        }
    }
}



