package com.aluguel.carros.model;

import com.aluguel.carros.enums.TipoUsuario;
import jakarta.persistence.*;

@Entity
public class Funcionario extends Usuario {
    @jakarta.validation.constraints.NotBlank(message = "Matrícula é obrigatória")
    private String matricula;

    @jakarta.validation.constraints.NotBlank(message = "Nome da empresa é obrigatório")
    private String nomeEmpresa;

    @jakarta.validation.constraints.NotBlank(message = "CNPJ da empresa é obrigatório")
    private String cnpjEmpresa;

    public Funcionario(){}

    public Funcionario(String nome, String email, String senha, String matricula, String nomeEmpresa, String cnpjEmpresa) {
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
        this.matricula = matricula;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpjEmpresa = cnpjEmpresa;
        this.setAtivo(true); // Define o funcionário como ativo por padrão
    }

    @Override
    public TipoUsuario getTipoUsuario() {
        return TipoUsuario.FUNCIONARIO;
    }

    // Getters e Setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }
}