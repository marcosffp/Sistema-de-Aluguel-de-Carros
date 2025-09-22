package com.aluguel.carros.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aluguel.carros.dto.AgenteBancarioRequestDTO;
import com.aluguel.carros.dto.AgenteBancarioResponseDTO;
import com.aluguel.carros.dto.AuthResponse;
import com.aluguel.carros.service.AgenteBancarioService;

@RestController
@RequestMapping("/agentes")
public class AgenteBancarioController {
    @Autowired
    private AgenteBancarioService agenteBancarioService;

    @GetMapping
    public ResponseEntity<List<AgenteBancarioResponseDTO>> listarTodos() {
        List<AgenteBancarioResponseDTO> agentes = agenteBancarioService.listarTodos();
        return ResponseEntity.ok(agentes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgenteBancarioResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<AgenteBancarioResponseDTO> agente = agenteBancarioService.buscarPorId(id);
        return agente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AuthResponse> criar(@RequestBody AgenteBancarioRequestDTO dto) {
        AuthResponse response = agenteBancarioService.criar(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgenteBancarioResponseDTO> atualizar(@PathVariable Long id,
            @RequestBody AgenteBancarioRequestDTO dto) {
        Optional<AgenteBancarioResponseDTO> agenteAtualizado = agenteBancarioService.atualizar(id, dto);
        return agenteAtualizado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = agenteBancarioService.deletar(id);
        return deletado ? ResponseEntity.noContent().build() 
                        : ResponseEntity.notFound().build();
    }
}