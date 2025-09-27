package com.aluguel.carros.service;

import com.aluguel.carros.dto.EmpregadorClienteRequestDTO;
import com.aluguel.carros.dto.EmpregadorClienteResponseDTO;
import com.aluguel.carros.model.Cliente;
import com.aluguel.carros.model.EmpregadorCliente;
import com.aluguel.carros.repository.ClienteRepository;
import com.aluguel.carros.repository.EmpregadorClienteRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmpregadorClienteService {

  @Autowired private EmpregadorClienteRepository empregadorRepository;

  @Autowired private ClienteRepository clienteRepository;

  public List<EmpregadorClienteResponseDTO> listarPorCliente(Long clienteId) {
    List<EmpregadorCliente> empregadores = empregadorRepository.findByClienteId(clienteId);
    return empregadores.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
  }

  public Optional<EmpregadorClienteResponseDTO> buscarPorId(Long id) {
    return empregadorRepository.findById(id).map(this::convertToResponseDTO);
  }

  public EmpregadorClienteResponseDTO criarEmpregador(
      Long clienteId, EmpregadorClienteRequestDTO requestDTO) {
    Cliente cliente =
        clienteRepository
            .findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    // Verificar se o cliente já possui 3 empregadores (regra de negócio)
    Long quantidadeEmpregadores = empregadorRepository.countByCliente(cliente);
    if (quantidadeEmpregadores >= 3) {
      throw new IllegalArgumentException("Cliente só pode ter até 3 empregadores cadastrados");
    }

    EmpregadorCliente empregador =
        new EmpregadorCliente(requestDTO.getNomeEmpregador(), requestDTO.getRendimento(), cliente);

    EmpregadorCliente savedEmpregador = empregadorRepository.save(empregador);
    return convertToResponseDTO(savedEmpregador);
  }

  public EmpregadorClienteResponseDTO atualizarEmpregador(
      Long clienteId, Long empregadorId, EmpregadorClienteRequestDTO requestDTO) {
    EmpregadorCliente empregador =
        empregadorRepository
            .findById(empregadorId)
            .orElseThrow(() -> new RuntimeException("Empregador não encontrado"));

    // Verificar se o empregador pertence ao cliente
    if (!empregador.getCliente().getId().equals(clienteId)) {
      throw new IllegalArgumentException("Empregador não pertence ao cliente informado");
    }

    empregador.setNomeEmpregador(requestDTO.getNomeEmpregador());
    empregador.setRendimento(requestDTO.getRendimento());

    EmpregadorCliente updatedEmpregador = empregadorRepository.save(empregador);
    return convertToResponseDTO(updatedEmpregador);
  }

  public void excluirEmpregador(Long clienteId, Long empregadorId) {
    EmpregadorCliente empregador =
        empregadorRepository
            .findById(empregadorId)
            .orElseThrow(() -> new RuntimeException("Empregador não encontrado"));

    // Verificar se o empregador pertence ao cliente
    if (!empregador.getCliente().getId().equals(clienteId)) {
      throw new IllegalArgumentException("Empregador não pertence ao cliente informado");
    }

    empregadorRepository.delete(empregador);
  }

  public BigDecimal calcularRendimentoTotalDoCliente(Long clienteId) {
    Cliente cliente =
        clienteRepository
            .findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    BigDecimal rendimentoTotal = empregadorRepository.calcularRendimentoTotalDoCliente(cliente);
    return rendimentoTotal != null ? rendimentoTotal : BigDecimal.ZERO;
  }

  // Métodos de conversão
  private EmpregadorClienteResponseDTO convertToResponseDTO(EmpregadorCliente empregador) {
    return new EmpregadorClienteResponseDTO(
        empregador.getId(),
        empregador.getNomeEmpregador(),
        empregador.getRendimento(),
        empregador.getCliente().getId(),
        empregador.getCliente().getNome());
  }
}
