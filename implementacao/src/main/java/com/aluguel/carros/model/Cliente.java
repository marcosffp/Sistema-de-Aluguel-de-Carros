package com.aluguel.carros.model;

import com.aluguel.carros.enums.TipoUsuario;
import jakarta.persistence.*;

@Entity
public class Cliente extends Usuario {
    @jakarta.validation.constraints.NotBlank(message = "RG é obrigatório")
    private String rg;

    @jakarta.validation.constraints.NotBlank(message = "CPF é obrigatório")
    // Para validação de CPF, pode-se usar uma anotação customizada ou apenas o padrão regex:
    // @jakarta.validation.constraints.Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido")
    private String cpf;

    @jakarta.validation.constraints.NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    @jakarta.validation.constraints.NotBlank(message = "Profissão é obrigatória")
    private String profissao;

    public Cliente(){}

    public Cliente(String nome, String email, String rg, String cpf, String endereco, String profissao, String senha) {
        this.setNome(nome);
        this.setEmail(email);
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = endereco;
        this.profissao = profissao;
        this.setAtivo(true); // Define o cliente como ativo por padrão
    }

    @Override
    public TipoUsuario getTipoUsuario() {
        return TipoUsuario.CLIENTE;
    }

    // Getters e Setters
    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }
}