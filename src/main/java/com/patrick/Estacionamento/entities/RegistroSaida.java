package com.patrick.Estacionamento.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class RegistroSaida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Veiculo veiculo;

    private LocalDateTime dataHoraSaida;
    private double valorPago;

    public RegistroSaida(Veiculo veiculo, LocalDateTime dataHoraSaida, double valorPago) {
        this.veiculo = veiculo;
        this.dataHoraSaida = dataHoraSaida;
        this.valorPago = valorPago;
    }


}

