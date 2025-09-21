package com.aluguel.carros.dto;

import com.aluguel.carros.model.Cliente;

public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String rg;
    private String cpf;
    private String endereco;
    private String profissao;

    public ClienteResponseDTO(Long id, String nome, String email, String rg, String cpf, String endereco, String profissao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = endereco;
        this.profissao = profissao;
    }

    public ClienteResponseDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.rg = cliente.getRg();
        this.cpf = cliente.getCpf();
        this.endereco = cliente.getEndereco();
        this.profissao = cliente.getProfissao();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getProfissao() { return profissao; }
    public void setProfissao(String profissao) { this.profissao = profissao; }
}
