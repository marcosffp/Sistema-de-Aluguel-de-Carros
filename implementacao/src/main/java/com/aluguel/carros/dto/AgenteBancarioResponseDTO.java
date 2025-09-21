package com.aluguel.carros.dto;

import com.aluguel.carros.model.AgenteBancario;

public class AgenteBancarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String cnpjBanco;
    private String nomeBanco;

    public AgenteBancarioResponseDTO() {}

    public AgenteBancarioResponseDTO(AgenteBancario agenteBancario) {
        this.id = agenteBancario.getId();
        this.nome = agenteBancario.getNome();
        this.email = agenteBancario.getEmail();
        this.cnpjBanco = agenteBancario.getCnpjBanco();
        this.nomeBanco = agenteBancario.getNomeBanco();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCnpjBanco() { return cnpjBanco; }
    public void setCnpjBanco(String cnpjBanco) { this.cnpjBanco = cnpjBanco; }
    public String getNomeBanco() { return nomeBanco; }
    public void setNomeBanco(String nomeBanco) { this.nomeBanco = nomeBanco; }
}
