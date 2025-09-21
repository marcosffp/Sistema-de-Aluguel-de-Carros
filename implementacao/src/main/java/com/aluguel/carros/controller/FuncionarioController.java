package com.aluguel.carros.controller;

import com.aluguel.carros.dto.FuncionarioCadastroDTO;
import com.aluguel.carros.dto.FuncionarioResponseDTO;
import com.aluguel.carros.model.Funcionario;
import com.aluguel.carros.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<FuncionarioResponseDTO> listarTodos() {
        return funcionarioService.listarTodos().stream()
                .map(FuncionarioResponseDTO::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioService.buscarPorId(id);
        return funcionario.map(f -> ResponseEntity.ok(new FuncionarioResponseDTO(f))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FuncionarioResponseDTO criar(@RequestBody FuncionarioCadastroDTO funcionario) {
        Funcionario novoFuncionario = new Funcionario(funcionario.getNome(), funcionario.getEmail(), funcionario.getSenha(), funcionario.getMatricula());
        return new FuncionarioResponseDTO(funcionarioService.salvar(novoFuncionario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        if (!funcionarioService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        funcionario.setId(id);
        return ResponseEntity.ok(new FuncionarioResponseDTO(funcionarioService.salvar(funcionario)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!funcionarioService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}