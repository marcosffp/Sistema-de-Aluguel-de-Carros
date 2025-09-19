package com.aluguel.carros.controller;

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
    public List<AgenteBancario> listarTodos() {
        return agenteBancarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgenteBancario> buscarPorId(@PathVariable Long id) {
        Optional<AgenteBancario> agenteBancario = agenteBancarioService.buscarPorId(id);
        return agenteBancario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AgenteBancario criar(@RequestBody AgenteBancario agenteBancario) {
        return agenteBancarioService.salvar(agenteBancario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgenteBancario> atualizar(@PathVariable Long id, @RequestBody AgenteBancario agenteBancario) {
        if (!agenteBancarioService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        agenteBancario.setId(id);
        return ResponseEntity.ok(agenteBancarioService.salvar(agenteBancario));
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