package com.patrick.Estacionamento.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class RegistroSaida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "registro_entrada_id")
    private RegistroEntrada registroEntrada;  // Relacionamento com a entrada

    private LocalDateTime dataHoraSaida;
    private double valorPago;

    // Removido o veiculo pois já está na entrada
    public RegistroSaida(RegistroEntrada registroEntrada, LocalDateTime dataHoraSaida, double valorPago) {
        this.registroEntrada = registroEntrada;
        this.dataHoraSaida = dataHoraSaida;
        this.valorPago = valorPago;
    }

}

