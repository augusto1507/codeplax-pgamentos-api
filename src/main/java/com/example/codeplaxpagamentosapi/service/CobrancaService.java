package com.example.codeplaxpagamentosapi.service;

import com.example.codeplaxpagamentosapi.dto.cobrancas.CobrancaCriarDto;
import com.example.codeplaxpagamentosapi.dto.eventos.EventoRespostaDto;
import com.example.codeplaxpagamentosapi.enums.StatusCobranca;
import com.example.codeplaxpagamentosapi.exeptions.NaoEncontradoExeptions;
import com.example.codeplaxpagamentosapi.exeptions.ValorDivergenteException;
import com.example.codeplaxpagamentosapi.models.Cobranca;
import com.example.codeplaxpagamentosapi.repositories.CobrancasRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;


@Service
public class CobrancaService {
    private final CobrancasRepository repository;

    private final RestClient bilheteriaClient;

    public CobrancaService(CobrancasRepository repository, @Qualifier("bilheteriaClient") RestClient bilheteriaClient)
    {this.repository = repository;
    this.bilheteriaClient = bilheteriaClient;}



    @Transactional
    public Cobranca criar(CobrancaCriarDto dado) {

        var evento = obterEvento(dado.eventoId());
        var ValorEsperado = evento.preco().multiply(BigDecimal.valueOf(dado.quantidade()));
        if (dado.valor().compareTo(valorEsperado) =! 0){
            throw new ValorDivergenteException();
        }

    var cobranca = Cobranca.builder()
            .valor(dado.valor())
            .cartaoFinal(dado.cartaoFinal())
            .status("0000".equals(dado.cartaoFinal() ? StatusCobranca.RECUSADO : StatusCobranca.APROVADO : StatusCobranca.PENDENTE)
            .tentativasWebHook(0)
            .eventoId(dado.eventoId())
            .quantidade(dado.quantidade())
            .webhook(false)
            .build();

        return this.repository.save(cobranca);

    }

    @Transactional
    public Cobranca obterPorId(int id) {
        var cobranca = repository.findById(id).orElseThrow(() -> new NaoEncontradoExeptions("Evento não encontrado"));
        return cobranca;

    }

    private EventoRespostaDto obterEvento(Integer id){
        EventoRespostaDto evento = bilheteriaClient.get()
                .uri("/eventos/{id}", id)
                .retrieve()
                .body(EventoRespostaDto.class);
        return evento;
    }
}
