package com.aluguel.carros.controller;

import com.aluguel.carros.dto.AvaliacaoPedidoDTO;
import com.aluguel.carros.dto.PedidoAluguelRequestDTO;
import com.aluguel.carros.dto.PedidoAluguelResponseDTO;
import com.aluguel.carros.enums.StatusPedido;
import com.aluguel.carros.service.PedidoAluguelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
@Tag(
    name = "Pedidos de Aluguel",
    description = "Operações relacionadas ao gerenciamento de pedidos de aluguel")
public class PedidoAluguelController {

  @Autowired private PedidoAluguelService pedidoAluguelService;

  // ===== ENDPOINTS PARA CLIENTES =====

  @PostMapping("/cliente/{clienteId}")
  @PreAuthorize("hasRole('CLIENTE') and #clienteId == authentication.principal.id")
  public ResponseEntity<PedidoAluguelResponseDTO> criarPedido(
      @PathVariable Long clienteId, @Valid @RequestBody PedidoAluguelRequestDTO requestDTO) {
    try {
      PedidoAluguelResponseDTO novoPedido = pedidoAluguelService.criarPedido(clienteId, requestDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/cliente/{clienteId}")
  @PreAuthorize("hasRole('CLIENTE') and #clienteId == authentication.principal.id")
  public ResponseEntity<List<PedidoAluguelResponseDTO>> listarPedidosDoCliente(
      @PathVariable Long clienteId) {
    List<PedidoAluguelResponseDTO> pedidos = pedidoAluguelService.listarPedidosDoCliente(clienteId);
    return ResponseEntity.ok(pedidos);
  }

  @PutMapping("/cliente/{clienteId}/{pedidoId}")
  @PreAuthorize("hasRole('CLIENTE') and #clienteId == authentication.principal.id")
  public ResponseEntity<PedidoAluguelResponseDTO> atualizarPedidoCliente(
      @PathVariable Long clienteId,
      @PathVariable Long pedidoId,
      @Valid @RequestBody PedidoAluguelRequestDTO requestDTO) {
    try {
      PedidoAluguelResponseDTO pedidoAtualizado =
          pedidoAluguelService.atualizarPedidoCliente(clienteId, pedidoId, requestDTO);
      return ResponseEntity.ok(pedidoAtualizado);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/cliente/{clienteId}/{pedidoId}")
  @PreAuthorize("hasRole('CLIENTE') and #clienteId == authentication.principal.id")
  public ResponseEntity<Void> cancelarPedido(
      @PathVariable Long clienteId, @PathVariable Long pedidoId) {
    try {
      pedidoAluguelService.cancelarPedido(clienteId, pedidoId);
      return ResponseEntity.noContent().build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  // ===== ENDPOINTS PARA FUNCIONÁRIOS =====

  @GetMapping("/funcionario/para-analise")
  @PreAuthorize("hasRole('FUNCIONARIO')")
  public ResponseEntity<List<PedidoAluguelResponseDTO>> listarPedidosParaAnalise() {
    List<PedidoAluguelResponseDTO> pedidos = pedidoAluguelService.listarPedidosParaAnalise();
    return ResponseEntity.ok(pedidos);
  }

  @GetMapping("/funcionario/{funcionarioId}")
  @PreAuthorize("hasRole('FUNCIONARIO') and #funcionarioId == authentication.principal.id")
  public ResponseEntity<List<PedidoAluguelResponseDTO>> listarPedidosDoFuncionario(
      @PathVariable Long funcionarioId) {
    List<PedidoAluguelResponseDTO> pedidos =
        pedidoAluguelService.listarPedidosDoFuncionario(funcionarioId);
    return ResponseEntity.ok(pedidos);
  }

  @PostMapping("/funcionario/{funcionarioId}/assumir/{pedidoId}")
  @PreAuthorize("hasRole('FUNCIONARIO') and #funcionarioId == authentication.principal.id")
  public ResponseEntity<PedidoAluguelResponseDTO> assumirPedido(
      @PathVariable Long funcionarioId, @PathVariable Long pedidoId) {
    try {
      PedidoAluguelResponseDTO pedido = pedidoAluguelService.assumirPedido(funcionarioId, pedidoId);
      return ResponseEntity.ok(pedido);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PatchMapping("/funcionario/{funcionarioId}/avaliar/{pedidoId}")
  @PreAuthorize("hasRole('FUNCIONARIO') and #funcionarioId == authentication.principal.id")
  public ResponseEntity<PedidoAluguelResponseDTO> avaliarPedido(
      @PathVariable Long funcionarioId,
      @PathVariable Long pedidoId,
      @Valid @RequestBody AvaliacaoPedidoDTO avaliacaoDTO) {
    try {
      PedidoAluguelResponseDTO pedidoAvaliado =
          pedidoAluguelService.avaliarPedido(funcionarioId, pedidoId, avaliacaoDTO);
      return ResponseEntity.ok(pedidoAvaliado);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/funcionario/{funcionarioId}/encaminhar/{pedidoId}/agente/{agenteBancarioId}")
  @PreAuthorize("hasRole('FUNCIONARIO') and #funcionarioId == authentication.principal.id")
  public ResponseEntity<PedidoAluguelResponseDTO> encaminharParaAgenteBancario(
      @PathVariable Long funcionarioId,
      @PathVariable Long pedidoId,
      @PathVariable Long agenteBancarioId) {
    try {
      PedidoAluguelResponseDTO pedidoEncaminhado =
          pedidoAluguelService.encaminharParaAgenteBancario(
              funcionarioId, pedidoId, agenteBancarioId);
      return ResponseEntity.ok(pedidoEncaminhado);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  // ===== ENDPOINTS PARA AGENTES BANCÁRIOS =====

  @GetMapping("/agente-bancario/{agenteBancarioId}")
  @PreAuthorize("hasRole('AGENTE_BANCARIO') and #agenteBancarioId == authentication.principal.id")
  public ResponseEntity<List<PedidoAluguelResponseDTO>> listarPedidosDoAgenteBancario(
      @PathVariable Long agenteBancarioId) {
    List<PedidoAluguelResponseDTO> pedidos =
        pedidoAluguelService.listarPedidosDoAgenteBancario(agenteBancarioId);
    return ResponseEntity.ok(pedidos);
  }

  @PatchMapping("/agente-bancario/{agenteBancarioId}/analisar/{pedidoId}")
  @PreAuthorize("hasRole('AGENTE_BANCARIO') and #agenteBancarioId == authentication.principal.id")
  public ResponseEntity<PedidoAluguelResponseDTO> analisarPedido(
      @PathVariable Long agenteBancarioId,
      @PathVariable Long pedidoId,
      @Valid @RequestBody AvaliacaoPedidoDTO avaliacaoDTO) {
    try {
      PedidoAluguelResponseDTO pedidoAnalisado =
          pedidoAluguelService.analisarPedido(agenteBancarioId, pedidoId, avaliacaoDTO);
      return ResponseEntity.ok(pedidoAnalisado);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  // ===== ENDPOINTS GERAIS =====

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyRole('CLIENTE', 'FUNCIONARIO', 'AGENTE_BANCARIO')")
  public ResponseEntity<PedidoAluguelResponseDTO> buscarPorId(
      @PathVariable Long id, Authentication authentication) {
    Optional<PedidoAluguelResponseDTO> pedido = pedidoAluguelService.buscarPorId(id);

    if (pedido.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    // Verificar se o usuário tem permissão para ver este pedido
    PedidoAluguelResponseDTO pedidoDTO = pedido.get();
    Long usuarioId = (Long) authentication.getPrincipal();

    boolean temPermissao = false;
    for (GrantedAuthority authority : authentication.getAuthorities()) {
      switch (authority.getAuthority()) {
        case "ROLE_CLIENTE":
          temPermissao = pedidoDTO.getClienteId().equals(usuarioId);
          break;
        case "ROLE_FUNCIONARIO":
          temPermissao =
              pedidoDTO.getFuncionarioId() != null
                  && pedidoDTO.getFuncionarioId().equals(usuarioId);
          break;
        case "ROLE_AGENTE_BANCARIO":
          temPermissao =
              pedidoDTO.getAgenteBancarioId() != null
                  && pedidoDTO.getAgenteBancarioId().equals(usuarioId);
          break;
      }
      if (temPermissao) break;
    }

    if (!temPermissao) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    return ResponseEntity.ok(pedidoDTO);
  }

  @GetMapping
  @PreAuthorize("hasRole('FUNCIONARIO')")
  public ResponseEntity<List<PedidoAluguelResponseDTO>> listarTodos() {
    List<PedidoAluguelResponseDTO> pedidos = pedidoAluguelService.listarTodos();
    return ResponseEntity.ok(pedidos);
  }

  @GetMapping("/status/{status}")
  @PreAuthorize("hasRole('FUNCIONARIO')")
  public ResponseEntity<List<PedidoAluguelResponseDTO>> listarPorStatus(
      @PathVariable StatusPedido status) {
    List<PedidoAluguelResponseDTO> pedidos = pedidoAluguelService.listarPorStatus(status);
    return ResponseEntity.ok(pedidos);
  }
}
