package com.aluguel.carros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "veiculo")
public class Veiculo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Matrícula é obrigatória")
  @Column(unique = true)
  private String matricula;

  @NotNull(message = "Ano é obrigatório")
  private Integer ano;

  @NotBlank(message = "Marca é obrigatória")
  private String marca;

  @NotBlank(message = "Modelo é obrigatório")
  private String modelo;

  @NotBlank(message = "Placa é obrigatória")
  @Column(unique = true)
  private String placa;

  @Column(name = "disponivel")
  private Boolean disponivel = true;

  @Embedded
  private Proprietario proprietario;

  // Construtores
  public Veiculo() {}

  public Veiculo(String matricula, Integer ano, String marca, String modelo, String placa) {
    this.matricula = matricula;
    this.ano = ano;
    this.marca = marca;
    this.modelo = modelo;
    this.placa = placa;
    this.disponivel = true;
    this.proprietario = null;
  }

  public Veiculo(
      String matricula,
      Integer ano,
      String marca,
      String modelo,
      String placa,
      Proprietario proprietario) {
    this.matricula = matricula;
    this.ano = ano;
    this.marca = marca;
    this.modelo = modelo;
    this.placa = placa;
    this.disponivel = true;
    this.proprietario = proprietario;
  }

  // Getters e Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public Integer getAno() {
    return ano;
  }

  public void setAno(Integer ano) {
    this.ano = ano;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public String getModelo() {
    return modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public Boolean getDisponivel() {
    return disponivel;
  }

  public void setDisponivel(Boolean disponivel) {
    this.disponivel = disponivel;
  }

  public Proprietario getProprietario() {
    return proprietario;
  }

  public void setProprietario(Proprietario proprietario) {
    this.proprietario = proprietario;
  }
}
