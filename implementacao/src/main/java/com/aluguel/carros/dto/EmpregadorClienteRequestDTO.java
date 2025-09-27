package com.aluguel.carros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class EmpregadorClienteRequestDTO {
  @NotBlank(message = "Nome do empregador é obrigatório")
  private String nomeEmpregador;

  @NotNull(message = "Rendimento é obrigatório")
  @PositiveOrZero(message = "Rendimento deve ser positivo ou zero")
  private BigDecimal rendimento;

  // Construtores
  public EmpregadorClienteRequestDTO() {}

  public EmpregadorClienteRequestDTO(String nomeEmpregador, BigDecimal rendimento) {
    this.nomeEmpregador = nomeEmpregador;
    this.rendimento = rendimento;
  }

  // Getters e Setters
  public String getNomeEmpregador() {
    return nomeEmpregador;
  }

  public void setNomeEmpregador(String nomeEmpregador) {
    this.nomeEmpregador = nomeEmpregador;
  }

  public BigDecimal getRendimento() {
    return rendimento;
  }

  public void setRendimento(BigDecimal rendimento) {
    this.rendimento = rendimento;
  }
}
