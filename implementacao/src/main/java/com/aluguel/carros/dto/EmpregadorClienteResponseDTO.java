package com.aluguel.carros.dto;

import java.math.BigDecimal;

public class EmpregadorClienteResponseDTO {
  private Long id;
  private String nomeEmpregador;
  private BigDecimal rendimento;
  private Long clienteId;
  private String clienteNome;

  // Construtores
  public EmpregadorClienteResponseDTO() {}

  public EmpregadorClienteResponseDTO(
      Long id, String nomeEmpregador, BigDecimal rendimento, Long clienteId, String clienteNome) {
    this.id = id;
    this.nomeEmpregador = nomeEmpregador;
    this.rendimento = rendimento;
    this.clienteId = clienteId;
    this.clienteNome = clienteNome;
  }

  // Getters e Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public Long getClienteId() {
    return clienteId;
  }

  public void setClienteId(Long clienteId) {
    this.clienteId = clienteId;
  }

  public String getClienteNome() {
    return clienteNome;
  }

  public void setClienteNome(String clienteNome) {
    this.clienteNome = clienteNome;
  }
}
