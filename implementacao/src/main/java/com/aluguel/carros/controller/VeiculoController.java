package com.aluguel.carros.controller;

import com.aluguel.carros.dto.VeiculoRequestDTO;
import com.aluguel.carros.dto.VeiculoResponseDTO;
import com.aluguel.carros.model.Usuario;
import com.aluguel.carros.service.VeiculoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veiculos")
@CrossOrigin(origins = "*")
@Tag(name = "Veículos", description = "Operações relacionadas ao gerenciamento de veículos")
public class VeiculoController {

  @Autowired private VeiculoService veiculoService;

  @GetMapping
  @PreAuthorize("hasRole('FUNCIONARIO')")
  public ResponseEntity<List<VeiculoResponseDTO>> listarTodos() {
    List<VeiculoResponseDTO> veiculos = veiculoService.listarTodos();
    return ResponseEntity.ok(veiculos);
  }

  @GetMapping("/disponiveis")
  public ResponseEntity<List<VeiculoResponseDTO>> listarDisponiveis() {
    List<VeiculoResponseDTO> veiculos = veiculoService.listarDisponiveis();
    return ResponseEntity.ok(veiculos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<VeiculoResponseDTO> buscarPorId(@PathVariable Long id) {
    Optional<VeiculoResponseDTO> veiculo = veiculoService.buscarPorId(id);
    return veiculo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @PreAuthorize("hasRole('FUNCIONARIO')")
  public ResponseEntity<VeiculoResponseDTO> criarVeiculo(
      @Valid @RequestBody VeiculoRequestDTO requestDTO,
      Authentication authentication) {
    try {
      Long funcionarioId = ((Usuario) authentication.getPrincipal()).getId();
      VeiculoResponseDTO novoVeiculo = veiculoService.criarVeiculo(requestDTO, funcionarioId);
      return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('FUNCIONARIO')")
  public ResponseEntity<VeiculoResponseDTO> atualizarVeiculo(
      @PathVariable Long id, @Valid @RequestBody VeiculoRequestDTO requestDTO) {
    try {
      VeiculoResponseDTO veiculoAtualizado = veiculoService.atualizarVeiculo(id, requestDTO);
      return ResponseEntity.ok(veiculoAtualizado);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('FUNCIONARIO')")
  public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id) {
    try {
      veiculoService.excluirVeiculo(id);
      return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PatchMapping("/{id}/indisponivel")
  @PreAuthorize("hasRole('FUNCIONARIO')")
  public ResponseEntity<Void> marcarComoIndisponivel(@PathVariable Long id) {
    try {
      veiculoService.marcarComoIndisponivel(id);
      return ResponseEntity.ok().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PatchMapping("/{id}/disponivel")
  @PreAuthorize("hasRole('FUNCIONARIO')")
  public ResponseEntity<Void> marcarComoDisponivel(@PathVariable Long id) {
    try {
      veiculoService.marcarComoDisponivel(id);
      return ResponseEntity.ok().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/buscar-por-marca")
  public ResponseEntity<List<VeiculoResponseDTO>> buscarPorMarca(@RequestParam String marca) {
    List<VeiculoResponseDTO> veiculos = veiculoService.buscarPorMarca(marca);
    return ResponseEntity.ok(veiculos);
  }

  @GetMapping("/buscar-por-modelo")
  public ResponseEntity<List<VeiculoResponseDTO>> buscarPorModelo(@RequestParam String modelo) {
    List<VeiculoResponseDTO> veiculos = veiculoService.buscarPorModelo(modelo);
    return ResponseEntity.ok(veiculos);
  }
}
