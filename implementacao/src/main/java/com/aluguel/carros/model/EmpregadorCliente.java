package com.aluguel.carros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
@Table(name = "empregador_cliente")
public class EmpregadorCliente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Nome do empregador é obrigatório")
  private String nomeEmpregador;

  @NotNull(message = "Rendimento é obrigatório")
  @PositiveOrZero(message = "Rendimento deve ser positivo ou zero")
  private BigDecimal rendimento;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  // Construtores
  public EmpregadorCliente() {}

  public EmpregadorCliente(String nomeEmpregador, BigDecimal rendimento, Cliente cliente) {
    this.nomeEmpregador = nomeEmpregador;
    this.rendimento = rendimento;
    this.cliente = cliente;
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

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }
}
