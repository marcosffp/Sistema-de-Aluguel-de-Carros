package com.aluguel.carros.controller;

import com.aluguel.carros.dto.AgenteBancarioRequestDTO;
import com.aluguel.carros.dto.AgenteBancarioResponseDTO;
import com.aluguel.carros.model.AgenteBancario;
import com.aluguel.carros.service.AgenteBancarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agentes")
public class AgenteBancarioController {
    @Autowired
    private AgenteBancarioService agenteBancarioService;

    @GetMapping
    public List<AgenteBancarioResponseDTO> listarTodos() {
        return agenteBancarioService.listarTodos().stream()
                .map(AgenteBancarioResponseDTO::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgenteBancarioResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<AgenteBancario> agenteBancario = agenteBancarioService.buscarPorId(id);
        return agenteBancario.map(AgenteBancarioResponseDTO::new).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AgenteBancarioResponseDTO criar(@RequestBody AgenteBancarioRequestDTO dto) {
        AgenteBancario agenteBancario = new AgenteBancario(dto.getNome(), dto.getEmail(), dto.getSenha(), dto.getCnpjBanco(), dto.getNomeBanco());
        return new AgenteBancarioResponseDTO(agenteBancarioService.salvar(agenteBancario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgenteBancarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody AgenteBancarioRequestDTO dto) {
        if (!agenteBancarioService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        AgenteBancario agenteBancario = new AgenteBancario(dto.getNome(), dto.getEmail(), dto.getSenha(), dto.getCnpjBanco(), dto.getNomeBanco());
        agenteBancario.setId(id);
        return ResponseEntity.ok(new AgenteBancarioResponseDTO(agenteBancarioService.salvar(agenteBancario)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!agenteBancarioService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        agenteBancarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}