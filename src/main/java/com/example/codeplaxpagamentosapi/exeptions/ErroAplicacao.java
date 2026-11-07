package com.example.codeplaxpagamentosapi.exeptions;

import org.springframework.http.HttpStatus;

public class ErroAplicacao extends RuntimeException {
    private final HttpStatus status;
    private final String codigo;


    protected ErroAplicacao(HttpStatus status, String codigo, String mensagem){
        super(mensagem);
        this.status = status;
        this.codigo = codigo;
    }
}
