package com.aluguel.carros.controller;

import com.aluguel.carros.model.Cliente;
import com.aluguel.carros.service.ClienteService;
import com.aluguel.carros.dto.ClienteCadastroDTO;
import com.aluguel.carros.dto.ClienteResponseDTO;

import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteResponseDTO> listarTodos() {
        return clienteService.listarTodos().stream()
                .map(ClienteResponseDTO::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        return cliente.map(c -> ResponseEntity.ok(new ClienteResponseDTO(c))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClienteResponseDTO criar(@Validated @RequestBody ClienteCadastroDTO dto) {
        Cliente cliente = new Cliente(dto.getNome(), dto.getEmail(), dto.getRg(), dto.getCpf(), dto.getEndereco(), dto.getProfissao(), dto.getSenha());
        ClienteResponseDTO responseDTO = new ClienteResponseDTO(clienteService.salvar(cliente));
        return responseDTO;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        if (!clienteService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(id);
        ClienteResponseDTO responseDTO = new ClienteResponseDTO(clienteService.salvar(cliente));
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!clienteService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}