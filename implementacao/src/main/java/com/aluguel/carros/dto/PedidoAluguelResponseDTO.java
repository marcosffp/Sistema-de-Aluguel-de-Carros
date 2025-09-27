package com.aluguel.carros.dto;

import com.aluguel.carros.enums.StatusPedido;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PedidoAluguelResponseDTO {
  private Long id;
  private Long clienteId;
  private String clienteNome;
  private VeiculoResponseDTO veiculo;
  private Long funcionarioId;
  private String funcionarioNome;
  private Long agenteBancarioId;
  private String agenteBancarioNome;
  private LocalDate dataInicio;
  private LocalDate dataFim;
  private BigDecimal valorTotal;
  private StatusPedido status;
  private String observacoes;
  private Boolean solicitaCredito;
  private LocalDateTime dataCriacao;
  private LocalDateTime dataUltimaAtualizacao;
  private String motivoReprovacao;

  // Construtores
  public PedidoAluguelResponseDTO() {}

  public PedidoAluguelResponseDTO(
      Long id,
      Long clienteId,
      String clienteNome,
      VeiculoResponseDTO veiculo,
      LocalDate dataInicio,
      LocalDate dataFim,
      BigDecimal valorTotal,
      StatusPedido status,
      Boolean solicitaCredito,
      LocalDateTime dataCriacao) {
    this.id = id;
    this.clienteId = clienteId;
    this.clienteNome = clienteNome;
    this.veiculo = veiculo;
    this.dataInicio = dataInicio;
    this.dataFim = dataFim;
    this.valorTotal = valorTotal;
    this.status = status;
    this.solicitaCredito = solicitaCredito;
    this.dataCriacao = dataCriacao;
  }

  // Getters e Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public VeiculoResponseDTO getVeiculo() {
    return veiculo;
  }

  public void setVeiculo(VeiculoResponseDTO veiculo) {
    this.veiculo = veiculo;
  }

  public Long getFuncionarioId() {
    return funcionarioId;
  }

  public void setFuncionarioId(Long funcionarioId) {
    this.funcionarioId = funcionarioId;
  }

  public String getFuncionarioNome() {
    return funcionarioNome;
  }

  public void setFuncionarioNome(String funcionarioNome) {
    this.funcionarioNome = funcionarioNome;
  }

  public Long getAgenteBancarioId() {
    return agenteBancarioId;
  }

  public void setAgenteBancarioId(Long agenteBancarioId) {
    this.agenteBancarioId = agenteBancarioId;
  }

  public String getAgenteBancarioNome() {
    return agenteBancarioNome;
  }

  public void setAgenteBancarioNome(String agenteBancarioNome) {
    this.agenteBancarioNome = agenteBancarioNome;
  }

  public LocalDate getDataInicio() {
    return dataInicio;
  }

  public void setDataInicio(LocalDate dataInicio) {
    this.dataInicio = dataInicio;
  }

  public LocalDate getDataFim() {
    return dataFim;
  }

  public void setDataFim(LocalDate dataFim) {
    this.dataFim = dataFim;
  }

  public BigDecimal getValorTotal() {
    return valorTotal;
  }

  public void setValorTotal(BigDecimal valorTotal) {
    this.valorTotal = valorTotal;
  }

  public StatusPedido getStatus() {
    return status;
  }

  public void setStatus(StatusPedido status) {
    this.status = status;
  }

  public String getObservacoes() {
    return observacoes;
  }

  public void setObservacoes(String observacoes) {
    this.observacoes = observacoes;
  }

  public Boolean getSolicitaCredito() {
    return solicitaCredito;
  }

  public void setSolicitaCredito(Boolean solicitaCredito) {
    this.solicitaCredito = solicitaCredito;
  }

  public LocalDateTime getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(LocalDateTime dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public LocalDateTime getDataUltimaAtualizacao() {
    return dataUltimaAtualizacao;
  }

  public void setDataUltimaAtualizacao(LocalDateTime dataUltimaAtualizacao) {
    this.dataUltimaAtualizacao = dataUltimaAtualizacao;
  }

  public String getMotivoReprovacao() {
    return motivoReprovacao;
  }

  public void setMotivoReprovacao(String motivoReprovacao) {
    this.motivoReprovacao = motivoReprovacao;
  }
}
