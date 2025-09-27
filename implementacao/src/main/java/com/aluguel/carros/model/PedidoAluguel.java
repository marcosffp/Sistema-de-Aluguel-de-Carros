package com.aluguel.carros.model;

import com.aluguel.carros.enums.StatusPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido_aluguel")
public class PedidoAluguel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cliente_id", nullable = false)
  private Cliente cliente;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "veiculo_id", nullable = false)
  private Veiculo veiculo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "funcionario_id")
  private Funcionario funcionario;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "agente_bancario_id")
  private AgenteBancario agenteBancario;

  @NotNull(message = "Data de início é obrigatória")
  @FutureOrPresent(message = "Data de início deve ser hoje ou no futuro")
  @Column(name = "data_inicio")
  private LocalDate dataInicio;

  @NotNull(message = "Data de fim é obrigatória")
  @Column(name = "data_fim")
  private LocalDate dataFim;

  @NotNull(message = "Valor total é obrigatório")
  @PositiveOrZero(message = "Valor total deve ser positivo ou zero")
  @Column(name = "valor_total")
  private BigDecimal valorTotal;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private StatusPedido status = StatusPedido.AGUARDANDO_ANALISE;

  @Column(name = "observacoes", columnDefinition = "TEXT")
  private String observacoes;

  @Column(name = "solicita_credito")
  private Boolean solicitaCredito = false;

  @Column(name = "data_criacao")
  private LocalDateTime dataCriacao;

  @Column(name = "data_ultima_atualizacao")
  private LocalDateTime dataUltimaAtualizacao;

  @Column(name = "motivo_reprovacao", columnDefinition = "TEXT")
  private String motivoReprovacao;

  @PrePersist
  protected void onCreate() {
    dataCriacao = LocalDateTime.now();
    dataUltimaAtualizacao = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    dataUltimaAtualizacao = LocalDateTime.now();
  }

  // Construtores
  public PedidoAluguel() {}

  public PedidoAluguel(
      Cliente cliente,
      Veiculo veiculo,
      LocalDate dataInicio,
      LocalDate dataFim,
      BigDecimal valorTotal) {
    this.cliente = cliente;
    this.veiculo = veiculo;
    this.dataInicio = dataInicio;
    this.dataFim = dataFim;
    this.valorTotal = valorTotal;
    this.status = StatusPedido.AGUARDANDO_ANALISE;
    this.solicitaCredito = false;
  }

  // Getters e Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Veiculo getVeiculo() {
    return veiculo;
  }

  public void setVeiculo(Veiculo veiculo) {
    this.veiculo = veiculo;
  }

  public Funcionario getFuncionario() {
    return funcionario;
  }

  public void setFuncionario(Funcionario funcionario) {
    this.funcionario = funcionario;
  }

  public AgenteBancario getAgenteBancario() {
    return agenteBancario;
  }

  public void setAgenteBancario(AgenteBancario agenteBancario) {
    this.agenteBancario = agenteBancario;
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

  // Métodos de validação
  public boolean podeSerCancelado() {
    return status == StatusPedido.AGUARDANDO_ANALISE || status == StatusPedido.EM_ANALISE;
  }

  public boolean podeSerEditado() {
    return status == StatusPedido.AGUARDANDO_ANALISE;
  }

  public boolean podeSerAvaliado() {
    return status == StatusPedido.AGUARDANDO_ANALISE || status == StatusPedido.EM_ANALISE;
  }
}
