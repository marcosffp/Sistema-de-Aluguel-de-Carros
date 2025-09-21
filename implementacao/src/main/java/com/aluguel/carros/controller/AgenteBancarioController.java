package com.aluguel.carros.controller;

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
    public AgenteBancarioResponseDTO criar(@RequestBody AgenteBancario agenteBancario) {
        return new AgenteBancarioResponseDTO(agenteBancarioService.salvar(agenteBancario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgenteBancarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody AgenteBancario agenteBancario) {
        if (!agenteBancarioService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
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