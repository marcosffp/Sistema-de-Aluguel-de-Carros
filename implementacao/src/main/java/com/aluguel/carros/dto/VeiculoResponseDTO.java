package com.aluguel.carros.dto;

public class VeiculoResponseDTO {
  private Long id;
  private String matricula;
  private Integer ano;
  private String marca;
  private String modelo;
  private String placa;
  private Boolean disponivel;

  // Construtores
  public VeiculoResponseDTO() {}

  public VeiculoResponseDTO(
      Long id,
      String matricula,
      Integer ano,
      String marca,
      String modelo,
      String placa,
      Boolean disponivel) {
    this.id = id;
    this.matricula = matricula;
    this.ano = ano;
    this.marca = marca;
    this.modelo = modelo;
    this.placa = placa;
    this.disponivel = disponivel;
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
}
