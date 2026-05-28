package com.furb.web2.model_Equipamento;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String descricao;
    private Date dtRecebimento;

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDtRecebimento() {
        return dtRecebimento;
    }

    public void setDtRecebimento(Date dtRecebimento) {
        this.dtRecebimento = dtRecebimento;
    }
}
