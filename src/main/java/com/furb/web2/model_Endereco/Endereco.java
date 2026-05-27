package com.furb.web2.model_Endereco;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furb.web2.model_Usuario.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;
    private String bairro;
    private String cidade;
    @OneToMany(mappedBy = "endereco")
    private List<Usuario> usuarios;

    public Endereco(){}

    public Long getId() {
        return id;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "endereco")
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
