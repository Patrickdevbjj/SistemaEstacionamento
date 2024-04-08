package com.patrick.Estacionamento.controllers;

import com.patrick.Estacionamento.entities.RegistroEntrada;
import com.patrick.Estacionamento.entities.Veiculo;
import com.patrick.Estacionamento.repositories.RegistroEntradaRepository;
import com.patrick.Estacionamento.repositories.VeiculoRepository;
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

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private RegistroEntradaRepository registroEntradaRepository;

    @PostMapping("/entrada")
    public ResponseEntity<String> registrarEntrada(@RequestBody Veiculo veiculo) {
        Veiculo veiculoExistente = veiculoRepository.findByPlate(veiculo.getPlaca());
        if (veiculoExistente == null) {
            veiculoRepository.save(veiculo);
        }

        LocalDateTime dataHoraEntrada = LocalDateTime.now();

        RegistroEntrada registroEntrada = new RegistroEntrada();
        registroEntrada.setVeiculo(veiculoExistente != null ? veiculoExistente : veiculo);
        registroEntrada.setDataHoraEntrada(dataHoraEntrada);

        registroEntradaRepository.save(registroEntrada);

        return ResponseEntity.status(HttpStatus.CREATED).body("Veículo com placa " + registroEntrada.getVeiculo().getPlaca() + " registrado com sucesso!");
    }
}


