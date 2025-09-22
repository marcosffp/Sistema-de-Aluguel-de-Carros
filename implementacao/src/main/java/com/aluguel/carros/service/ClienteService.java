package com.aluguel.carros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aluguel.carros.dto.AuthResponse;
import com.aluguel.carros.dto.ClienteRequestDTO;
import com.aluguel.carros.dto.ClienteResponseDTO;
import com.aluguel.carros.model.Cliente;
import com.aluguel.carros.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public List<ClienteResponseDTO> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(ClienteResponseDTO::new)
                .toList();
    }

    public Optional<ClienteResponseDTO> buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .map(ClienteResponseDTO::new);
    }

    public AuthResponse criar(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente(dto.getNome(), dto.getEmail(), dto.getRg(), 
                dto.getCpf(), dto.getEndereco(), dto.getProfissao(), dto.getSenha());
        
        if (cliente.getSenha() != null) {
            String senhaCriptografada = passwordEncoder.encode(cliente.getSenha());
            cliente.setSenha(senhaCriptografada);
        }
        
        Cliente clienteSalvo = clienteRepository.save(cliente);

        // Gerar token
        String token = jwtService.generateToken(clienteSalvo);

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setTipoUsuario(clienteSalvo.getTipoUsuario());
        response.setNome(clienteSalvo.getNome());
        response.setExpiresIn(jwtService.getExpirationTime());

        return response;
    }

    public Optional<ClienteResponseDTO> atualizar(Long id, ClienteRequestDTO dto) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isEmpty()) {
            return Optional.empty();
        }

        Cliente cliente = clienteExistente.get();
        // Atualizar apenas os campos permitidos (sem ID)
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setRg(dto.getRg());
        cliente.setCpf(dto.getCpf());
        cliente.setEndereco(dto.getEndereco());
        cliente.setProfissao(dto.getProfissao());
        
        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            cliente.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return Optional.of(new ClienteResponseDTO(clienteAtualizado));
    }

    public boolean deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            return false;
        }
        clienteRepository.deleteById(id);
        return true;
    }
}