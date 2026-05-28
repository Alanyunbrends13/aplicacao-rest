package com.furb.web2.model_Equipamento;

import java.util.Date;

public record EquipamentoRequest(
    String descricao,
    Date dtRecebimento
) {}
