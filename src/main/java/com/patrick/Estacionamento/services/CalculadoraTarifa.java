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

    // Valores configuráveis (podem ser injetados via properties)
    private final double valorDiaria = 70.0;
    private final double valorPorHora = 10.0;
    private final int minutosTolerancia = 15;
    private final int horasMinimasParaDiaria = 9;

    @Override
    public double calcular(LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaida) {
        // Validação fundamental
        if (dataHoraSaida.isBefore(dataHoraEntrada)) {
            throw new IllegalArgumentException("Data de saída não pode ser anterior à entrada");
        }

        // Calcula a duração total
        Duration duracao = Duration.between(dataHoraEntrada, dataHoraSaida);
        long minutosTotais = duracao.toMinutes();

        // Caso 1: Diária (9h ou mais)
        if (duracao.toHours() >= horasMinimasParaDiaria) {
            return valorDiaria;
        }

        // Caso 2: Dentro da tolerância (15 minutos ou menos)
        if (minutosTotais <= minutosTolerancia) {
            return 0.0;
        }

        // Caso 3: Horas avulsas (arredondadas para cima)
        long horasCobradas = (minutosTotais + 59) / 60; // Ex.: 61 minutos → 2 horas
        double valorAvulso = horasCobradas * valorPorHora;

        // Garante que a diária não seja mais cara que horas avulsas
        return Math.min(valorAvulso, valorDiaria);
    }
}
