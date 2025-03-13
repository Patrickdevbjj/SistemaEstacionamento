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

@RestController
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    @PostMapping("/entrada")
    public ResponseEntity<String> registrarEntrada(@RequestBody Veiculo veiculo) {
        // Validação básica dos dados recebidos
        if (veiculo == null || veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A placa do veículo é obrigatória.");
        }

        try {
            // Chama o método da service para registrar a entrada
            entradaService.registrarEntrada(veiculo, LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.CREATED).body("Veículo com placa " + veiculo.getPlaca() + " registrado com sucesso!");
        } catch (Exception e) {
            // Tratamento de exceções
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao registrar a entrada: " + e.getMessage());
        }
    }
}



