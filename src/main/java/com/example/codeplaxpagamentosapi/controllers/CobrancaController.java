package com.example.codeplaxpagamentosapi.controllers;


import com.example.codeplaxpagamentosapi.dto.cobrancas.CobrancaCriarDto;
import com.example.codeplaxpagamentosapi.models.Cobranca;
import com.example.codeplaxpagamentosapi.service.CobrancaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cobrancas")
@Tag(name= "cobrancas", description = "Pagamento")
public class CobrancaController {
    private final CobrancaService service;

    public CobrancaController(CobrancaService service){this.service = service;}

    @PostMapping
    @Operation( description = "final 0000 é recusado")
    public Cobranca criar(@RequestBody @Valid CobrancaCriarDto dados){
        return service.criar(dados);
    }

    @GetMapping("/{id}")
    @Operation
    public Cobranca obterPorID(@PathVariable Integer id){
        return service.obterPorId(id);
    }
}
