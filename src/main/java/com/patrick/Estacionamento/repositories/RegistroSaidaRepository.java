package com.patrick.Estacionamento.repositories;

import com.patrick.Estacionamento.entities.RegistroSaida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroSaidaRepository extends JpaRepository<RegistroSaida, Long> {

}

