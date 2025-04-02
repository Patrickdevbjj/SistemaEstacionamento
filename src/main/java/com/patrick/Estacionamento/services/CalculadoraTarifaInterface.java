package com.patrick.Estacionamento.services;


import java.time.LocalDateTime;

public interface CalculadoraTarifaInterface {

    /**
     * Calcula o valor a ser cobrado com base no tempo de permanência
     * @param dataHoraEntrada Momento em que o veículo entrou
     * @param dataHoraSaida Momento em que o veículo está saindo
     * @return Valor a ser cobrado
     * @throws IllegalArgumentException Se dataHoraSaida for anterior à dataHoraEntrada
     */
    double calcular(LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaida);

    /**
     * Versão simplificada que usa o momento atual como saída
     */
    default double calcular(LocalDateTime dataHoraEntrada) {
        return calcular(dataHoraEntrada, LocalDateTime.now());
    }
}