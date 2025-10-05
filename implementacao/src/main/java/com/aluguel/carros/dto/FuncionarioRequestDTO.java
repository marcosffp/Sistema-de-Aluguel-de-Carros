package com.aluguel.carros.dto;

public class FuncionarioRequestDTO {
    private String nome;
    private String email;
    private String senha;
    private String matricula;
    private String nomeEmpresa;
    private String cnpjEmpresa;

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }
    public String getCnpjEmpresa() { return cnpjEmpresa; }
    public void setCnpjEmpresa(String cnpjEmpresa) { this.cnpjEmpresa = cnpjEmpresa; }
}
