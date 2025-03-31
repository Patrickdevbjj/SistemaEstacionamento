package com.patrick.Estacionamento.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class RegistroEntrada {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  // Alterado para ManyToOne (um veículo pode ter múltiplas entradas)
    @NotNull(message = "O veículo é obrigatório para registrar uma entrada")
    private Veiculo veiculo;

    private LocalDateTime dataHoraEntrada;

    @OneToOne(mappedBy = "registroEntrada", cascade = CascadeType.ALL)
    private RegistroSaida registroSaida;

    public RegistroEntrada(Veiculo veiculo, LocalDateTime dataHoraEntrada) {
        this.veiculo = veiculo;
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public boolean estaAtivo() {
        return registroSaida == null;
    }



}
