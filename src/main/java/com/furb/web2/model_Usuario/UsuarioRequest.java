package com.furb.web2.model_Usuario;

public record UsuarioRequest(
        String nome,
        String rua,
        String idade,
        Long enderecoId
) {}