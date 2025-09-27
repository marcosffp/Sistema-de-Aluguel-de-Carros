package com.aluguel.carros.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoAluguelRequestDTO {
  @NotNull(message = "ID do veículo é obrigatório")
  private Long veiculoId;

  @NotNull(message = "Data de início é obrigatória")
  @FutureOrPresent(message = "Data de início deve ser hoje ou no futuro")
  private LocalDate dataInicio;

  @NotNull(message = "Data de fim é obrigatória")
  private LocalDate dataFim;

  @NotNull(message = "Valor total é obrigatório")
  @PositiveOrZero(message = "Valor total deve ser positivo ou zero")
  private BigDecimal valorTotal;

  private String observacoes;

  private Boolean solicitaCredito = false;

  // Construtores
  public PedidoAluguelRequestDTO() {}

  public PedidoAluguelRequestDTO(
      Long veiculoId, LocalDate dataInicio, LocalDate dataFim, BigDecimal valorTotal) {
    this.veiculoId = veiculoId;
    this.dataInicio = dataInicio;
    this.dataFim = dataFim;
    this.valorTotal = valorTotal;
    this.solicitaCredito = false;
  }

  // Getters e Setters
  public Long getVeiculoId() {
    return veiculoId;
  }

  public void setVeiculoId(Long veiculoId) {
    this.veiculoId = veiculoId;
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
}
