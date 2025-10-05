package com.aluguel.carros.controller;

import com.aluguel.carros.dto.EmpregadorClienteRequestDTO;
import com.aluguel.carros.dto.EmpregadorClienteResponseDTO;
import com.aluguel.carros.service.EmpregadorClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes/{clienteId}/empregadores")
@CrossOrigin(origins = "*")
@Tag(
    name = "Empregadores do Cliente",
    description = "Operações relacionadas aos empregadores cadastrados pelo cliente")
public class EmpregadorClienteController {

  @Autowired private EmpregadorClienteService empregadorService;

  @GetMapping
  @PreAuthorize("hasRole('CLIENTE') and #clienteId == authentication.principal.id")
  public ResponseEntity<List<EmpregadorClienteResponseDTO>> listarEmpregadores(
      @PathVariable Long clienteId) {
    List<EmpregadorClienteResponseDTO> empregadores = empregadorService.listarPorCliente(clienteId);
    return ResponseEntity.ok(empregadores);
  }

  @GetMapping("/{empregadorId}")
  @PreAuthorize("hasRole('CLIENTE') and #clienteId == authentication.principal.id")
  public ResponseEntity<EmpregadorClienteResponseDTO> buscarEmpregadorPorId(
      @PathVariable Long clienteId, @PathVariable Long empregadorId) {
    Optional<EmpregadorClienteResponseDTO> empregador = empregadorService.buscarPorId(empregadorId);

    if (empregador.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    // Verificar se o empregador pertence ao cliente
    if (!empregador.get().getClienteId().equals(clienteId)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    return ResponseEntity.ok(empregador.get());
  }

  @PostMapping
  @PreAuthorize("hasRole('CLIENTE') and #clienteId == authentication.principal.id")
  public ResponseEntity<EmpregadorClienteResponseDTO> criarEmpregador(
      @PathVariable Long clienteId, @Valid @RequestBody EmpregadorClienteRequestDTO requestDTO) {
    try {
      EmpregadorClienteResponseDTO novoEmpregador =
          empregadorService.criarEmpregador(clienteId, requestDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(novoEmpregador);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{empregadorId}")
  @PreAuthorize("hasRole('CLIENTE') and #clienteId == authentication.principal.id")
  public ResponseEntity<EmpregadorClienteResponseDTO> atualizarEmpregador(
      @PathVariable Long clienteId,
      @PathVariable Long empregadorId,
      @Valid @RequestBody EmpregadorClienteRequestDTO requestDTO) {
    try {
      EmpregadorClienteResponseDTO empregadorAtualizado =
          empregadorService.atualizarEmpregador(clienteId, empregadorId, requestDTO);
      return ResponseEntity.ok(empregadorAtualizado);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{empregadorId}")
  @PreAuthorize("hasRole('CLIENTE') and #clienteId == authentication.principal.id")
  public ResponseEntity<Void> excluirEmpregador(
      @PathVariable Long clienteId, @PathVariable Long empregadorId) {
    try {
      empregadorService.excluirEmpregador(clienteId, empregadorId);
      return ResponseEntity.noContent().build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/rendimento-total")
  @PreAuthorize("hasRole('CLIENTE') and #clienteId == authentication.principal.id")
  public ResponseEntity<BigDecimal> calcularRendimentoTotal(@PathVariable Long clienteId) {
    try {
      BigDecimal rendimentoTotal = empregadorService.calcularRendimentoTotalDoCliente(clienteId);
      return ResponseEntity.ok(rendimentoTotal);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
