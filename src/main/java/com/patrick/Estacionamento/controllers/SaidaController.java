package com.patrick.Estacionamento.controllers;


import com.patrick.Estacionamento.entities.RegistroSaida;
import com.patrick.Estacionamento.entities.RegistroEntrada;
import com.patrick.Estacionamento.services.CalculadoraTarifa;
import com.patrick.Estacionamento.services.CalculadoraTarifaInterface;
import com.patrick.Estacionamento.services.SaidaService;
import com.patrick.Estacionamento.services.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/saidas")
public class SaidaController {

    @Autowired
    private SaidaService saidaService;

    @Autowired
    private EntradaService entradaService;

    @Autowired  // Injeção de dependência
    private CalculadoraTarifa calculadoraTarifa;

    @Autowired
    private CalculadoraTarifaInterface calculadoraTarifaService;
    private Long entradaId;

    // Método para exibir o formulário de saída
    @GetMapping("/registrar")
    public String mostrarFormularioSaida(Model model) {
        model.addAttribute("registroSaida", new RegistroSaida());
        return "saida/formulario";
    }

    // Método para processar a saída
    @PostMapping("/registrar")
    public String registrarSaida(@RequestParam String placa, Model model) {
        try {
            // Busca o registro de entrada ativo para a placa
            RegistroEntrada registroEntrada = entradaService.buscarEntradaAtivaPorPlaca(placa);

            if (registroEntrada == null) {
                model.addAttribute("erro", "Nenhum veículo com esta placa está estacionado.");
                return "saida/formulario";
            }

            // Calcula o valor a pagar usando a data/hora atual como saída
            double valor = calculadoraTarifa.calcular(
                    registroEntrada.getDataHoraEntrada(),
                    LocalDateTime.now() // Adiciona a data/hora atual como saída
            );

            // Cria e salva o registro de saída
            RegistroSaida registroSaida = saidaService.registrarSaida(registroEntrada, valor, entradaId);

            model.addAttribute("registroSaida", registroSaida);
            model.addAttribute("veiculo", registroEntrada.getVeiculo());
            return "saida/comprovante";

        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao processar saída: " + e.getMessage());
            return "saida/formulario";
        }
    }

    // Método para exibir comprovante
    @GetMapping("/comprovante/{id}")
    public String mostrarComprovante(@PathVariable Long id, Model model) {
        RegistroSaida registroSaida = saidaService.buscarPorId(id);
        model.addAttribute("registroSaida", registroSaida);
        model.addAttribute("veiculo", registroSaida.getRegistroEntrada().getVeiculo());
        return "saida/comprovante";
    }
}