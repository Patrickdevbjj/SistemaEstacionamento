package com.patrick.Estacionamento.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Veiculo {

    @Id
    private Long id;

    private String placa;
    private String modelo;
    private LocalDateTime entrada;
    private LocalDateTime saida;

}
