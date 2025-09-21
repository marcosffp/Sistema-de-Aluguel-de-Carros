package com.aluguel.carros.dto;

public class AgenteBancarioCadastroDTO {
    private String nome;
    private String email;
    private String senha;
    private String cnpjBanco;
    private String nomeBanco;

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getCnpjBanco() { return cnpjBanco; }
    public void setCnpjBanco(String cnpjBanco) { this.cnpjBanco = cnpjBanco; }
    public String getNomeBanco() { return nomeBanco; }
    public void setNomeBanco(String nomeBanco) { this.nomeBanco = nomeBanco; }
}
