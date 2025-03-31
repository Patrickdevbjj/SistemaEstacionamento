package com.patrick.Estacionamento.services;

import com.patrick.Estacionamento.entities.RegistroEntrada;
import com.patrick.Estacionamento.entities.Veiculo;
import com.patrick.Estacionamento.repositories.RegistroEntradaRepository;
import com.patrick.Estacionamento.repositories.VeiculoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Transactional
@Service
public class EntradaService {

    @Autowired
    private RegistroEntradaRepository registroEntradaRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public void registrarEntrada(Veiculo veiculo, LocalDateTime dataHoraEntrada) {
        // Verifica se o veículo já existe no banco de dados
        Veiculo veiculoExistente = veiculoRepository.findByPlaca(veiculo.getPlaca());
        if (veiculoExistente == null) {
            // Se não existir, salva o veículo
            veiculoExistente = veiculoRepository.save(veiculo);
        }

        // Cria o registro de entrada
        RegistroEntrada registroEntrada = new RegistroEntrada(veiculoExistente, dataHoraEntrada);

        // Salva o registro de entrada
        registroEntradaRepository.save(registroEntrada);
    }
}
