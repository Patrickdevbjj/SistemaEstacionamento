package com.patrick.Estacionamento.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "O veiculo é obrigatorio para registrar uma entrada")
    private Veiculo veiculo;

    private LocalDateTime dataHoraEntrada;

    public RegistroEntrada(Veiculo veiculo, LocalDateTime dataHoraEntrada) {
        this.veiculo = veiculo;
        this.dataHoraEntrada = dataHoraEntrada;
    }


}
