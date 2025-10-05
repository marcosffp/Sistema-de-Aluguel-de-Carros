package com.aluguel.carros.dto;

import com.aluguel.carros.model.Funcionario;

public class FuncionarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String matricula;
    private String nomeEmpresa;
    private String cnpjEmpresa;

    public FuncionarioResponseDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.email = funcionario.getEmail();
        this.matricula = funcionario.getMatricula();
        this.nomeEmpresa = funcionario.getNomeEmpresa();
        this.cnpjEmpresa = funcionario.getCnpjEmpresa();
    }

    public FuncionarioResponseDTO(Long id, String nome, String email, String matricula, String nomeEmpresa, String cnpjEmpresa) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpjEmpresa = cnpjEmpresa;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }
    public String getCnpjEmpresa() { return cnpjEmpresa; }
    public void setCnpjEmpresa(String cnpjEmpresa) { this.cnpjEmpresa = cnpjEmpresa; }
}
