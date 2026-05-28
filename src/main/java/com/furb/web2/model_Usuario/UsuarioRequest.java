package com.furb.web2.model_Usuario;

public record UsuarioRequest(
        String nome,
        String idade,
        Long enderecoId
) {}