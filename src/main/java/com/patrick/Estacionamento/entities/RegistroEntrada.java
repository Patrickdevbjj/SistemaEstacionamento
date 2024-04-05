package com.patrick.Estacionamento.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class RegistroEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Veiculo veiculo;

    private LocalDateTime dataHoraEntrada;

    public RegistroEntrada(Veiculo veiculo, LocalDateTime dataHoraEntrada) {
        this.veiculo = veiculo;
        this.dataHoraEntrada = dataHoraEntrada;
    }


}
