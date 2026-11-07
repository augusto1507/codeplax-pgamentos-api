package com.example.codeplaxpagamentosapi.exeptions;

import org.springframework.http.HttpStatus;

public class NaoEncontradoException extends ErroAplicacao{
    public NaoEncontradoException(String mensagem){
        super(HttpStatus.NOT_FOUND, "nao_encontrado", mensagem);
    }
}
