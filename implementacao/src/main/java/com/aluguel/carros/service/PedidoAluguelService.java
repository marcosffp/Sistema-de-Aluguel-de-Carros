package com.aluguel.carros.service;

import com.aluguel.carros.dto.AvaliacaoPedidoDTO;
import com.aluguel.carros.dto.PedidoAluguelRequestDTO;
import com.aluguel.carros.dto.PedidoAluguelResponseDTO;
import com.aluguel.carros.dto.VeiculoResponseDTO;
import com.aluguel.carros.enums.StatusPedido;
import com.aluguel.carros.model.*;
import com.aluguel.carros.repository.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PedidoAluguelService {

  @Autowired private PedidoAluguelRepository pedidoRepository;

  @Autowired private ClienteRepository clienteRepository;

  @Autowired private VeiculoRepository veiculoRepository;

  @Autowired private FuncionarioRepository funcionarioRepository;

  @Autowired private AgenteBancarioRepository agenteBancarioRepository;

  @Autowired private VeiculoService veiculoService;

  // ===== OPERAÇÕES DO CLIENTE =====

  public PedidoAluguelResponseDTO criarPedido(Long clienteId, PedidoAluguelRequestDTO requestDTO) {
    Cliente cliente =
        clienteRepository
            .findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    Veiculo veiculo =
        veiculoRepository
            .findById(requestDTO.getVeiculoId())
            .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

    // Validar se o veículo está disponível
    if (!veiculo.getDisponivel()) {
      throw new IllegalArgumentException("Veículo não está disponível para aluguel");
    }

    // Validar datas
    if (requestDTO.getDataFim().isBefore(requestDTO.getDataInicio())) {
      throw new IllegalArgumentException("Data de fim deve ser posterior à data de início");
    }

    // Criar o pedido
    PedidoAluguel pedido =
        new PedidoAluguel(
            cliente,
            veiculo,
            requestDTO.getDataInicio(),
            requestDTO.getDataFim(),
            requestDTO.getValorTotal());

    pedido.setObservacoes(requestDTO.getObservacoes());
    pedido.setSolicitaCredito(requestDTO.getSolicitaCredito());

    PedidoAluguel savedPedido = pedidoRepository.save(pedido);
    return convertToResponseDTO(savedPedido);
  }

  public List<PedidoAluguelResponseDTO> listarPedidosDoCliente(Long clienteId) {
    List<PedidoAluguel> pedidos = pedidoRepository.findByClienteId(clienteId);
    return pedidos.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
  }

  public PedidoAluguelResponseDTO atualizarPedidoCliente(
      Long clienteId, Long pedidoId, PedidoAluguelRequestDTO requestDTO) {
    PedidoAluguel pedido =
        pedidoRepository
            .findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

    // Verificar se o pedido pertence ao cliente
    if (!pedido.getCliente().getId().equals(clienteId)) {
      throw new IllegalArgumentException("Pedido não pertence ao cliente informado");
    }

    // Verificar se o pedido pode ser editado
    if (!pedido.podeSerEditado()) {
      throw new IllegalArgumentException(
          "Pedido não pode ser editado no status atual: " + pedido.getStatus());
    }

    // Atualizar os dados
    if (requestDTO.getVeiculoId() != null
        && !requestDTO.getVeiculoId().equals(pedido.getVeiculo().getId())) {
      Veiculo novoVeiculo =
          veiculoRepository
              .findById(requestDTO.getVeiculoId())
              .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

      if (!novoVeiculo.getDisponivel()) {
        throw new IllegalArgumentException("Veículo não está disponível para aluguel");
      }

      pedido.setVeiculo(novoVeiculo);
    }

    // Validar datas
    if (requestDTO.getDataFim().isBefore(requestDTO.getDataInicio())) {
      throw new IllegalArgumentException("Data de fim deve ser posterior à data de início");
    }

    pedido.setDataInicio(requestDTO.getDataInicio());
    pedido.setDataFim(requestDTO.getDataFim());
    pedido.setValorTotal(requestDTO.getValorTotal());
    pedido.setObservacoes(requestDTO.getObservacoes());
    pedido.setSolicitaCredito(requestDTO.getSolicitaCredito());

    PedidoAluguel updatedPedido = pedidoRepository.save(pedido);
    return convertToResponseDTO(updatedPedido);
  }

  public void cancelarPedido(Long clienteId, Long pedidoId) {
    PedidoAluguel pedido =
        pedidoRepository
            .findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

    // Verificar se o pedido pertence ao cliente
    if (!pedido.getCliente().getId().equals(clienteId)) {
      throw new IllegalArgumentException("Pedido não pertence ao cliente informado");
    }

    // Verificar se o pedido pode ser cancelado
    if (!pedido.podeSerCancelado()) {
      throw new IllegalArgumentException(
          "Pedido não pode ser cancelado no status atual: " + pedido.getStatus());
    }

    pedido.setStatus(StatusPedido.CANCELADO);
    pedidoRepository.save(pedido);
  }

  // ===== OPERAÇÕES DO FUNCIONÁRIO =====

  public List<PedidoAluguelResponseDTO> listarPedidosParaAnalise() {
    List<PedidoAluguel> pedidos =
        pedidoRepository.findByStatusAndFuncionarioIsNull(StatusPedido.AGUARDANDO_ANALISE);
    return pedidos.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
  }

  public List<PedidoAluguelResponseDTO> listarPedidosDoFuncionario(Long funcionarioId) {
    Funcionario funcionario =
        funcionarioRepository
            .findById(funcionarioId)
            .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

    List<PedidoAluguel> pedidos = pedidoRepository.findByFuncionario(funcionario);
    return pedidos.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
  }

  public PedidoAluguelResponseDTO assumirPedido(Long funcionarioId, Long pedidoId) {
    Funcionario funcionario =
        funcionarioRepository
            .findById(funcionarioId)
            .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

    PedidoAluguel pedido =
        pedidoRepository
            .findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

    if (pedido.getStatus() != StatusPedido.AGUARDANDO_ANALISE) {
      throw new IllegalArgumentException("Pedido não está disponível para análise");
    }

    if (pedido.getFuncionario() != null) {
      throw new IllegalArgumentException("Pedido já foi assumido por outro funcionário");
    }

    pedido.setFuncionario(funcionario);
    pedido.setStatus(StatusPedido.EM_ANALISE);

    PedidoAluguel updatedPedido = pedidoRepository.save(pedido);
    return convertToResponseDTO(updatedPedido);
  }

  public PedidoAluguelResponseDTO avaliarPedido(
      Long funcionarioId, Long pedidoId, AvaliacaoPedidoDTO avaliacaoDTO) {
    // Verificar se funcionário existe
    funcionarioRepository
        .findById(funcionarioId)
        .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

    PedidoAluguel pedido =
        pedidoRepository
            .findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

    // Verificar se o funcionário pode avaliar o pedido
    if (pedido.getFuncionario() == null || !pedido.getFuncionario().getId().equals(funcionarioId)) {
      throw new IllegalArgumentException("Funcionário não pode avaliar este pedido");
    }

    if (!pedido.podeSerAvaliado()) {
      throw new IllegalArgumentException(
          "Pedido não pode ser avaliado no status atual: " + pedido.getStatus());
    }

    // Aplicar a avaliação
    pedido.setStatus(avaliacaoDTO.getStatus());

    if (avaliacaoDTO.getStatus() == StatusPedido.REPROVADO) {
      pedido.setMotivoReprovacao(avaliacaoDTO.getMotivoReprovacao());
    } else if (avaliacaoDTO.getStatus() == StatusPedido.APROVADO) {
      // Marcar veículo como indisponível
      veiculoService.marcarComoIndisponivel(pedido.getVeiculo().getId());
    }

    if (avaliacaoDTO.getObservacoes() != null) {
      pedido.setObservacoes(avaliacaoDTO.getObservacoes());
    }

    PedidoAluguel updatedPedido = pedidoRepository.save(pedido);
    return convertToResponseDTO(updatedPedido);
  }

  public PedidoAluguelResponseDTO encaminharParaAgenteBancario(
      Long funcionarioId, Long pedidoId, Long agenteBancarioId) {
    // Verificar se funcionário existe
    funcionarioRepository
        .findById(funcionarioId)
        .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

    AgenteBancario agenteBancario =
        agenteBancarioRepository
            .findById(agenteBancarioId)
            .orElseThrow(() -> new RuntimeException("Agente bancário não encontrado"));

    PedidoAluguel pedido =
        pedidoRepository
            .findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

    // Verificar se o funcionário pode encaminhar o pedido
    if (pedido.getFuncionario() == null || !pedido.getFuncionario().getId().equals(funcionarioId)) {
      throw new IllegalArgumentException("Funcionário não pode encaminhar este pedido");
    }

    if (pedido.getStatus() != StatusPedido.EM_ANALISE
        && pedido.getStatus() != StatusPedido.AGUARDANDO_ANALISE) {
      throw new IllegalArgumentException(
          "Pedido não pode ser encaminhado no status atual: " + pedido.getStatus());
    }

    pedido.setAgenteBancario(agenteBancario);
    pedido.setStatus(StatusPedido.EM_ANALISE);

    PedidoAluguel updatedPedido = pedidoRepository.save(pedido);
    return convertToResponseDTO(updatedPedido);
  }

  // ===== OPERAÇÕES DO AGENTE BANCÁRIO =====

  public List<PedidoAluguelResponseDTO> listarPedidosDoAgenteBancario(Long agenteBancarioId) {
    AgenteBancario agenteBancario =
        agenteBancarioRepository
            .findById(agenteBancarioId)
            .orElseThrow(() -> new RuntimeException("Agente bancário não encontrado"));

    List<StatusPedido> statusesRelevantes =
        Arrays.asList(StatusPedido.EM_ANALISE, StatusPedido.APROVADO, StatusPedido.REPROVADO);

    List<PedidoAluguel> pedidos =
        pedidoRepository.findByAgenteBancarioAndStatusIn(agenteBancario, statusesRelevantes);
    return pedidos.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
  }

  public PedidoAluguelResponseDTO analisarPedido(
      Long agenteBancarioId, Long pedidoId, AvaliacaoPedidoDTO avaliacaoDTO) {
    // Verificar se agente bancário existe
    agenteBancarioRepository
        .findById(agenteBancarioId)
        .orElseThrow(() -> new RuntimeException("Agente bancário não encontrado"));

    PedidoAluguel pedido =
        pedidoRepository
            .findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

    // Verificar se o agente bancário pode analisar o pedido
    if (pedido.getAgenteBancario() == null
        || !pedido.getAgenteBancario().getId().equals(agenteBancarioId)) {
      throw new IllegalArgumentException("Agente bancário não pode analisar este pedido");
    }

    if (!pedido.podeSerAvaliado()) {
      throw new IllegalArgumentException(
          "Pedido não pode ser analisado no status atual: " + pedido.getStatus());
    }

    // Aplicar a análise
    pedido.setStatus(avaliacaoDTO.getStatus());

    if (avaliacaoDTO.getStatus() == StatusPedido.REPROVADO) {
      pedido.setMotivoReprovacao(avaliacaoDTO.getMotivoReprovacao());
      // Liberar veículo se foi reprovado
      veiculoService.marcarComoDisponivel(pedido.getVeiculo().getId());
    } else if (avaliacaoDTO.getStatus() == StatusPedido.APROVADO) {
      // Marcar veículo como indisponível
      veiculoService.marcarComoIndisponivel(pedido.getVeiculo().getId());
    }

    if (avaliacaoDTO.getObservacoes() != null) {
      pedido.setObservacoes(avaliacaoDTO.getObservacoes());
    }

    PedidoAluguel updatedPedido = pedidoRepository.save(pedido);
    return convertToResponseDTO(updatedPedido);
  }

  // ===== OPERAÇÕES GERAIS =====

  public Optional<PedidoAluguelResponseDTO> buscarPorId(Long id) {
    return pedidoRepository.findById(id).map(this::convertToResponseDTO);
  }

  public List<PedidoAluguelResponseDTO> listarTodos() {
    List<PedidoAluguel> pedidos = pedidoRepository.findAll();
    return pedidos.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
  }

  public List<PedidoAluguelResponseDTO> listarPorStatus(StatusPedido status) {
    List<PedidoAluguel> pedidos = pedidoRepository.findByStatus(status);
    return pedidos.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
  }

  // ===== MÉTODOS DE CONVERSÃO =====

  private PedidoAluguelResponseDTO convertToResponseDTO(PedidoAluguel pedido) {
    PedidoAluguelResponseDTO dto = new PedidoAluguelResponseDTO();

    dto.setId(pedido.getId());
    dto.setClienteId(pedido.getCliente().getId());
    dto.setClienteNome(pedido.getCliente().getNome());

    // Converter veículo
    VeiculoResponseDTO veiculoDTO =
        new VeiculoResponseDTO(
            pedido.getVeiculo().getId(),
            pedido.getVeiculo().getMatricula(),
            pedido.getVeiculo().getAno(),
            pedido.getVeiculo().getMarca(),
            pedido.getVeiculo().getModelo(),
            pedido.getVeiculo().getPlaca(),
            pedido.getVeiculo().getDisponivel());
    dto.setVeiculo(veiculoDTO);

    if (pedido.getFuncionario() != null) {
      dto.setFuncionarioId(pedido.getFuncionario().getId());
      dto.setFuncionarioNome(pedido.getFuncionario().getNome());
    }

    if (pedido.getAgenteBancario() != null) {
      dto.setAgenteBancarioId(pedido.getAgenteBancario().getId());
      dto.setAgenteBancarioNome(pedido.getAgenteBancario().getNome());
    }

    dto.setDataInicio(pedido.getDataInicio());
    dto.setDataFim(pedido.getDataFim());
    dto.setValorTotal(pedido.getValorTotal());
    dto.setStatus(pedido.getStatus());
    dto.setObservacoes(pedido.getObservacoes());
    dto.setSolicitaCredito(pedido.getSolicitaCredito());
    dto.setDataCriacao(pedido.getDataCriacao());
    dto.setDataUltimaAtualizacao(pedido.getDataUltimaAtualizacao());
    dto.setMotivoReprovacao(pedido.getMotivoReprovacao());

    return dto;
  }
}
