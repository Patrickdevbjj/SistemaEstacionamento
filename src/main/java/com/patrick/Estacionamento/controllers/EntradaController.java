package com.patrick.Estacionamento.controllers;

import com.patrick.Estacionamento.entities.RegistroEntrada;
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
    private EntradaService registroEntradaService;


    @PostMapping("/entrada")
    public ResponseEntity<String> registrarEntrada(@RequestBody Veiculo veiculo) {

        // Crie um LocalDateTime para a data e hora de entrada
        LocalDateTime dataHoraEntrada = LocalDateTime.now();

        // Chame o serviço para registrar a entrada
        RegistroEntrada registroEntrada = registroEntradaService.registrarEntrada(new Veiculo(), dataHoraEntrada);

        // Retorne uma resposta de sucesso com a placa do veículo registrado
        return ResponseEntity.status(HttpStatus.CREATED).body("Veículo com placa " + registroEntrada.getVeiculo().getPlaca() + " registrado com sucesso!");
    }
}


