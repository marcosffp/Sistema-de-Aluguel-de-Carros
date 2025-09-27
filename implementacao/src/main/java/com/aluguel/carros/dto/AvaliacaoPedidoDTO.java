package com.aluguel.carros.dto;

import com.aluguel.carros.enums.StatusPedido;

public class AvaliacaoPedidoDTO {
  private StatusPedido status;
  private String motivoReprovacao;
  private String observacoes;
  private Long agenteBancarioId; // Para encaminhar para agente bancário

  // Construtores
  public AvaliacaoPedidoDTO() {}

  public AvaliacaoPedidoDTO(StatusPedido status, String motivoReprovacao) {
    this.status = status;
    this.motivoReprovacao = motivoReprovacao;
  }

  // Getters e Setters
  public StatusPedido getStatus() {
    return status;
  }

  public void setStatus(StatusPedido status) {
    this.status = status;
  }

  public String getMotivoReprovacao() {
    return motivoReprovacao;
  }

  public void setMotivoReprovacao(String motivoReprovacao) {
    this.motivoReprovacao = motivoReprovacao;
  }

  public String getObservacoes() {
    return observacoes;
  }

  public void setObservacoes(String observacoes) {
    this.observacoes = observacoes;
  }

  public Long getAgenteBancarioId() {
    return agenteBancarioId;
  }

  public void setAgenteBancarioId(Long agenteBancarioId) {
    this.agenteBancarioId = agenteBancarioId;
  }
}
