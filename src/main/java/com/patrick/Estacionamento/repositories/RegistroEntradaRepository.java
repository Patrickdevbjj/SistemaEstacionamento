package com.patrick.Estacionamento.repositories;

import com.patrick.Estacionamento.entities.RegistroEntrada;
import com.patrick.Estacionamento.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistroEntradaRepository extends JpaRepository<RegistroEntrada, Long> {

    RegistroEntrada findTopByVeiculoOrderByDataHoraEntradaDesc(Veiculo veiculo);

    Optional<RegistroEntrada> findByVeiculoPlacaAndDataHoraSaidaIsNull(String placa);
}



