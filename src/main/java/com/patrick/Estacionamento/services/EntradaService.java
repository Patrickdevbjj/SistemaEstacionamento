package com.patrick.Estacionamento.services;

import com.patrick.Estacionamento.entities.RegistroEntrada;
import com.patrick.Estacionamento.entities.Veiculo;
import com.patrick.Estacionamento.repositories.RegistroEntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntradaService {

    @Autowired
    private RegistroEntradaRepository registroEntradaRepository;


    public RegistroEntrada registrarEntrada(Veiculo veiculo, LocalDateTime dataHoraEntrada) {
        RegistroEntrada registroEntrada = new RegistroEntrada(veiculo, dataHoraEntrada);
        return registroEntradaRepository.save(registroEntrada);
    }
}

