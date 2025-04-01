package com.patrick.Estacionamento.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Getter
@AllArgsConstructor
public class CalculadoraTarifa implements CalculadoraTarifaInterface {
    // Valores padrão
    private final double valorDiaria = 70.0;
    private final double valorPorHora = 10.0;
    private final int minutosTolerancia = 15;
    private final int horasMinimasParaDiaria = 9;



    public double calcular(LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaida) {
        long minutos = Duration.between(dataHoraEntrada, dataHoraSaida).toMinutes();

        if (minutos <= minutosTolerancia) {
            return 0.0;
        }

        long horas = (minutos + 59) / 60;

        if (horas >= horasMinimasParaDiaria) {
            return valorDiaria;
        }

        return horas * valorPorHora;
    }
}
