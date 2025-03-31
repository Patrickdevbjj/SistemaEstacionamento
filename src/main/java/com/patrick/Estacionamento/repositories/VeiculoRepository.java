package com.patrick.Estacionamento.repositories;

import com.patrick.Estacionamento.entities.Veiculo;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {


    Veiculo findByPlaca(@NotBlank(message = "Obrigatório informar a placa") String placa);
}



