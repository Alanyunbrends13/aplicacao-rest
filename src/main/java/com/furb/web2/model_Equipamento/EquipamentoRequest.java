package com.furb.web2.model_Equipamento;

import java.time.LocalDate;

public record EquipamentoRequest(
    String descricao,
    LocalDate dtRecebimento
) {}
