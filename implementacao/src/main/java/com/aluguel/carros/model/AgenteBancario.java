package com.aluguel.carros.model;

import com.aluguel.carros.enums.TipoUsuario;
import jakarta.persistence.*;

@Entity
public class AgenteBancario extends Usuario {
    @jakarta.validation.constraints.NotBlank(message = "CNPJ do banco é obrigatório")
    private String cnpjBanco;

    @jakarta.validation.constraints.NotBlank(message = "Nome do banco é obrigatório")
    private String nomeBanco;

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