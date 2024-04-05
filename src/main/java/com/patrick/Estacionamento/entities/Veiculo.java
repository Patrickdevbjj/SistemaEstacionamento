package com.patrick.Estacionamento.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String placa;
    private String modelo;
    private LocalDateTime entrada;
    private LocalDateTime saida;

    public Veiculo(String placa, String modelo) {
        this.placa = placa;
        this.modelo = modelo;
        this.entrada = LocalDateTime.now(); // Registra a entrada automaticamente no momento da criação do veículo
    }

}