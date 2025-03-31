package com.patrick.Estacionamento.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class CalculadoraTarifa implements CalculadoraTarifaInterface {

    @Value("${Estacionamento.tarifa.hora:9.0}")
    private double valorPorHora;

    @Value("${Estacionamento.tarifa.diaria:70.0")
    private double valorDiaria;

    @Value("${estacionamento.tolerancia.minutos:15}")
    private int minutosTolerancia;

    @Value("${estacionamento.diaria.horas-minimas:7}")
    private int horasMinimasParaDiaria;

    @Override
    public double calcular(LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaida) {
        long minutos = Duration.between(dataHoraEntrada, dataHoraSaida).toMinutes();

        // Aplica tolerância gratuita
        if (minutos <= minutosTolerancia) {
            return 0.0;
        }

        // Calcula horas arredondando para cima
        long horas = (minutos + 59) / 60;  // Forma eficiente de arredondar para cima

        // Verifica se aplica tarifa diária
        if (horas >= horasMinimasParaDiaria) {
            return valorDiaria;
        }

        return horas * valorPorHora;
    }
}
