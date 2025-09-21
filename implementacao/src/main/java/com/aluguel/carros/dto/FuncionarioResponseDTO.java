package com.aluguel.carros.dto;

import com.aluguel.carros.model.Funcionario;

public class FuncionarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String matricula;

    public FuncionarioResponseDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.email = funcionario.getEmail();
        this.matricula = funcionario.getMatricula();
    }

    public FuncionarioResponseDTO(Long id, String nome, String email, String cargo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.matricula = cargo;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String cargo) { this.matricula = cargo; }
}
