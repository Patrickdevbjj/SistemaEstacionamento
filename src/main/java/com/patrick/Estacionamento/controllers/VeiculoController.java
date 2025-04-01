package com.patrick.Estacionamento.controllers;

import com.patrick.Estacionamento.entities.RegistroEntrada;
import com.patrick.Estacionamento.entities.Veiculo;
import com.patrick.Estacionamento.repositories.RegistroEntradaRepository;
import com.patrick.Estacionamento.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private RegistroEntradaRepository registroEntradaRepository;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listarVeiculos() {
        List<Veiculo> veiculos = veiculoRepository.findAll();

        List<Map<String, Object>> resultado = veiculos.stream().map(veiculo -> {
            Map<String, Object> dados = new HashMap<>();
            dados.put("placa", veiculo.getPlaca());

            RegistroEntrada ultimaEntrada = registroEntradaRepository
                    .findTopByVeiculoOrderByDataHoraEntradaDesc(veiculo);

            dados.put("ultimaEntrada",
                    ultimaEntrada != null ? ultimaEntrada.getDataHoraEntrada() : "Nunca entrou");

            return dados;
        }).toList();

        return ResponseEntity.ok(resultado);
    }
}

