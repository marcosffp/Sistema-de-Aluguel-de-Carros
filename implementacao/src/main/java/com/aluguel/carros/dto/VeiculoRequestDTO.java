package com.aluguel.carros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VeiculoRequestDTO {
  @NotBlank(message = "Matrícula é obrigatória")
  private String matricula;

  @NotNull(message = "Ano é obrigatório")
  private Integer ano;

  @NotBlank(message = "Marca é obrigatória")
  private String marca;

  @NotBlank(message = "Modelo é obrigatório")
  private String modelo;

  @NotBlank(message = "Placa é obrigatória")
  private String placa;

  // Construtores
  public VeiculoRequestDTO() {}

  public VeiculoRequestDTO(
      String matricula, Integer ano, String marca, String modelo, String placa) {
    this.matricula = matricula;
    this.ano = ano;
    this.marca = marca;
    this.modelo = modelo;
    this.placa = placa;
  }

  // Getters e Setters
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
}
