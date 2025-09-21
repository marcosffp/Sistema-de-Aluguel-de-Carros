package com.aluguel.carros.model;

import com.aluguel.carros.enums.TipoUsuario;
import jakarta.persistence.*;

@Entity
public class AgenteBancario extends Usuario {
    @jakarta.validation.constraints.NotBlank(message = "CNPJ do banco é obrigatório")
    private String cnpjBanco;

    @jakarta.validation.constraints.NotBlank(message = "Nome do banco é obrigatório")
    private String nomeBanco;

    public AgenteBancario(){}

    public AgenteBancario(String nome, String email, String senha, String cnpjBanco, String nomeBanco) {
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
        this.cnpjBanco = cnpjBanco;
        this.nomeBanco = nomeBanco;
        this.setAtivo(true); // Define o agente bancário como ativo por padrão
    }

    @Override
    public TipoUsuario getTipoUsuario() {
        return TipoUsuario.AGENTE_BANCARIO;
    }

    // Getters e Setters
    public String getCnpjBanco() {
        return cnpjBanco;
    }

    public void setCnpjBanco(String cnpjBanco) {
        this.cnpjBanco = cnpjBanco;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }
}