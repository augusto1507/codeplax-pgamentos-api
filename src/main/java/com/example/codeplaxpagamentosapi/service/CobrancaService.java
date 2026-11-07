package com.example.codeplaxpagamentosapi.service;

import com.example.codeplaxpagamentosapi.dto.cobrancas.CobrancaCriarDto;
import com.example.codeplaxpagamentosapi.enums.StatusCobranca;
import com.example.codeplaxpagamentosapi.exeptions.NaoEncontradoExeptions;
import com.example.codeplaxpagamentosapi.models.Cobranca;
import com.example.codeplaxpagamentosapi.repositories.CobrancasRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class CobrancaService {
    private final CobrancasRepository repository;

    public CobrancaService(CobrancasRepository repository) {this.repository = repository;}

    @Transactional
    public Cobranca criar(CobrancaCriarDto dado) {

    var cobranca = Cobranca.builder()
            .valor(dado.valor())
            .cartaoFinal(dado.cartaoFinal())
            .status(StatusCobranca.PENDENTE)
            .tentativasWebhook(0)
            .webhook(false)
            .build();

        return this.repository.save(cobranca);

    }

    @Transactional
    public Cobranca obterPorId(int id) {
        var cobranca = repository.findById(id).orElseThrow(() -> new NaoEncontradoExeptions("Evento não encontrado"));
        return cobranca;

    }
}
