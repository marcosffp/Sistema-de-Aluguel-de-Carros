package com.aluguel.carros.service;

import com.aluguel.carros.dto.VeiculoRequestDTO;
import com.aluguel.carros.dto.VeiculoResponseDTO;
import com.aluguel.carros.model.Veiculo;
import com.aluguel.carros.repository.VeiculoRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VeiculoService {

  @Autowired private VeiculoRepository veiculoRepository;

  public List<VeiculoResponseDTO> listarTodos() {
    List<Veiculo> veiculos = veiculoRepository.findAll();
    return veiculos.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
  }

  public List<VeiculoResponseDTO> listarDisponiveis() {
    List<Veiculo> veiculos = veiculoRepository.findVeiculosDisponiveis();
    return veiculos.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
  }

  public Optional<VeiculoResponseDTO> buscarPorId(Long id) {
    return veiculoRepository.findById(id).map(this::convertToResponseDTO);
  }

  public VeiculoResponseDTO criarVeiculo(VeiculoRequestDTO requestDTO) {
    // Verificar se matrícula já existe
    if (veiculoRepository.findByMatricula(requestDTO.getMatricula()).isPresent()) {
      throw new IllegalArgumentException("Já existe um veículo com esta matrícula");
    }

    // Verificar se placa já existe
    if (veiculoRepository.findByPlaca(requestDTO.getPlaca()).isPresent()) {
      throw new IllegalArgumentException("Já existe um veículo com esta placa");
    }

    Veiculo veiculo = convertToEntity(requestDTO);
    Veiculo savedVeiculo = veiculoRepository.save(veiculo);
    return convertToResponseDTO(savedVeiculo);
  }

  public VeiculoResponseDTO atualizarVeiculo(Long id, VeiculoRequestDTO requestDTO) {
    Veiculo veiculo =
        veiculoRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

    // Verificar se matrícula já existe (exceto para o próprio veículo)
    Optional<Veiculo> veiculoComMatricula =
        veiculoRepository.findByMatricula(requestDTO.getMatricula());
    if (veiculoComMatricula.isPresent() && !veiculoComMatricula.get().getId().equals(id)) {
      throw new IllegalArgumentException("Já existe outro veículo com esta matrícula");
    }

    // Verificar se placa já existe (exceto para o próprio veículo)
    Optional<Veiculo> veiculoComPlaca = veiculoRepository.findByPlaca(requestDTO.getPlaca());
    if (veiculoComPlaca.isPresent() && !veiculoComPlaca.get().getId().equals(id)) {
      throw new IllegalArgumentException("Já existe outro veículo com esta placa");
    }

    veiculo.setMatricula(requestDTO.getMatricula());
    veiculo.setAno(requestDTO.getAno());
    veiculo.setMarca(requestDTO.getMarca());
    veiculo.setModelo(requestDTO.getModelo());
    veiculo.setPlaca(requestDTO.getPlaca());

    Veiculo updatedVeiculo = veiculoRepository.save(veiculo);
    return convertToResponseDTO(updatedVeiculo);
  }

  public void excluirVeiculo(Long id) {
    Veiculo veiculo =
        veiculoRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

    veiculoRepository.delete(veiculo);
  }

  public void marcarComoIndisponivel(Long id) {
    Veiculo veiculo =
        veiculoRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

    veiculo.setDisponivel(false);
    veiculoRepository.save(veiculo);
  }

  public void marcarComoDisponivel(Long id) {
    Veiculo veiculo =
        veiculoRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

    veiculo.setDisponivel(true);
    veiculoRepository.save(veiculo);
  }

  public List<VeiculoResponseDTO> buscarPorMarca(String marca) {
    List<Veiculo> veiculos = veiculoRepository.findByMarcaContaining(marca);
    return veiculos.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
  }

  public List<VeiculoResponseDTO> buscarPorModelo(String modelo) {
    List<Veiculo> veiculos = veiculoRepository.findByModeloContaining(modelo);
    return veiculos.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
  }

  // Métodos de conversão
  private VeiculoResponseDTO convertToResponseDTO(Veiculo veiculo) {
    return new VeiculoResponseDTO(
        veiculo.getId(),
        veiculo.getMatricula(),
        veiculo.getAno(),
        veiculo.getMarca(),
        veiculo.getModelo(),
        veiculo.getPlaca(),
        veiculo.getDisponivel());
  }

  private Veiculo convertToEntity(VeiculoRequestDTO requestDTO) {
    return new Veiculo(
        requestDTO.getMatricula(),
        requestDTO.getAno(),
        requestDTO.getMarca(),
        requestDTO.getModelo(),
        requestDTO.getPlaca());
  }
}
