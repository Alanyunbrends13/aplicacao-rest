package com.furb.web2.model_Usuario;

import com.furb.web2.model_Endereco.Endereco;

import jakarta.persistence.*;

//deverá ter campo de senha para o CRUD
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String idade;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
