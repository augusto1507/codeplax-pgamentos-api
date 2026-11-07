package com.example.codeplaxpagamentosapi.dto.eventos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EventoRespostaDto(
        Integer id,
        BigDecimal preco,
        Integer quantidade

) {
}
