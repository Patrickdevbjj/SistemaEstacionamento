package com.patrick.Estacionamento.services;

import com.patrick.Estacionamento.entities.RegistroEntrada;
import com.patrick.Estacionamento.entities.RegistroSaida;
import com.patrick.Estacionamento.repositories.RegistroEntradaRepository;
import com.patrick.Estacionamento.repositories.RegistroSaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


    @Service
    public class SaidaService {

        @Autowired
        private RegistroEntradaRepository registroEntradaRepository;

        @Autowired
        private RegistroSaidaRepository registroSaidaRepository;

        @Transactional
        public RegistroSaida registrarSaida(RegistroEntrada registroEntrada, double valor, Long entradaId) {

            // 1. Busca a entrada pelo ID
            RegistroEntrada entrada = registroEntradaRepository.findById(entradaId)
                    .orElseThrow(() -> new RuntimeException("Entrada não encontrada com ID: " + entradaId));

            // 1. Verifica se já existe uma saída registrada
            if (registroEntrada.getRegistroSaida() != null) {
                throw new IllegalStateException("Já existe uma saída registrada para esta entrada");
            }

            // 2. Cria o novo registro de saída
            LocalDateTime dataHoraSaida = LocalDateTime.now();
            RegistroSaida registroSaida = new RegistroSaida(
                    registroEntrada,  // Associa a entrada correspondente
                    dataHoraSaida,    // Data/hora atual
                    valor             // Valor calculado
            );

            // 3. Salva o registro de saída
            RegistroSaida saidaSalva = registroSaidaRepository.save(registroSaida);

            // 4. Atualiza o relacionamento na entrada (opcional, depende do seu mapeamento)
            registroEntrada.setRegistroSaida(saidaSalva);
            registroEntradaRepository.save(registroEntrada);

            return saidaSalva;
        }

        public RegistroSaida buscarPorId(Long id) {
            return registroSaidaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Registro de saída não encontrado com ID: " + id));
        }
    }
