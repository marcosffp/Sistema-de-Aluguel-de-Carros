package com.aluguel.carros.model;

import com.aluguel.carros.enums.TipoUsuario;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @jakarta.validation.constraints.NotBlank(message = "Nome é obrigatório")
  private String nome;

  @jakarta.validation.constraints.Email(message = "Email inválido")
  @jakarta.validation.constraints.NotBlank(message = "Email é obrigatório")
  @jakarta.persistence.Column(unique = true)
  private String email;

  @jakarta.validation.constraints.NotBlank(message = "Senha é obrigatória")
  private String senha;

  private Boolean ativo;

  public abstract TipoUsuario getTipoUsuario();

  // Getters e Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    if( senha == null || senha.trim().isEmpty() || senha.length() < 6 ) {
      throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres.");
    }
    this.senha = senha;
  }

  public Boolean getAtivo() {
    return ativo;
  }

  public void setAtivo(Boolean ativo) {
    this.ativo = ativo;
  }
}
