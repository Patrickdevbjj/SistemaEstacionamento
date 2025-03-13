package com.patrick.Estacionamento.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Obrigatório informar a placa")
    private String placa;

    private LocalDateTime entrada;
    private LocalDateTime saida;

    public Veiculo(String placa) {
        this.placa = placa;
        this.entrada = LocalDateTime.now(); // Registra a entrada automaticamente no momento da criação do veículo
    }

}