package com.example.codeplaxpagamentosapi.dto.cobrancas;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CobrancaCriarDto (
    @NotNull
    @Positive
    @Schema(example = "300.00", description = "Valor maio que zero")
    BigDecimal valor,

    @NotBlank @Pattern(regexp = "[0-9]{4}")
    @JsonProperty("cartao_final")
    @Schema(example = "4242", description = "Somente os 4 últimos dígitos do cartão")
    String cartaoFinal
)
{}

