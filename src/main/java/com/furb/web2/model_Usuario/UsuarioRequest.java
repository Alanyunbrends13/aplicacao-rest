package com.furb.web2.model_Usuario;

import java.util.List;

public record UsuarioRequest(
        String nome,
        String idade,
        Long enderecoId,
        List<Long> equipamentos
) {}