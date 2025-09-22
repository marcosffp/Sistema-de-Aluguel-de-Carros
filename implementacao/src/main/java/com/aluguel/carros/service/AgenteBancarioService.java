package com.aluguel.carros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aluguel.carros.dto.AgenteBancarioRequestDTO;
import com.aluguel.carros.dto.AgenteBancarioResponseDTO;
import com.aluguel.carros.dto.AuthResponse;
import com.aluguel.carros.model.AgenteBancario;
import com.aluguel.carros.repository.AgenteBancarioRepository;

@Service
public class AgenteBancarioService {
    @Autowired
    private AgenteBancarioRepository agenteBancarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public List<AgenteBancarioResponseDTO> listarTodos() {
        return agenteBancarioRepository.findAll().stream()
                .map(AgenteBancarioResponseDTO::new)
                .toList();
    }

    public Optional<AgenteBancarioResponseDTO> buscarPorId(Long id) {
        return agenteBancarioRepository.findById(id)
                .map(AgenteBancarioResponseDTO::new);
    }

    public AuthResponse criar(AgenteBancarioRequestDTO dto) {
        AgenteBancario agenteBancario = new AgenteBancario(dto.getNome(), dto.getEmail(), 
                dto.getSenha(), dto.getCnpjBanco(), dto.getNomeBanco());
        
        if (agenteBancario.getSenha() != null) {
            String senhaCriptografada = passwordEncoder.encode(agenteBancario.getSenha());
            agenteBancario.setSenha(senhaCriptografada);
        }
        
        AgenteBancario agenteSalvo = agenteBancarioRepository.save(agenteBancario);

        // Gerar token
        String token = jwtService.generateToken(agenteSalvo);

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setTipoUsuario(agenteSalvo.getTipoUsuario());
        response.setNome(agenteSalvo.getNome());
        response.setExpiresIn(jwtService.getExpirationTime());

        return response;
    }

    public Optional<AgenteBancarioResponseDTO> atualizar(Long id, AgenteBancarioRequestDTO dto) {
        Optional<AgenteBancario> agenteExistente = agenteBancarioRepository.findById(id);
        if (agenteExistente.isEmpty()) {
            return Optional.empty();
        }

        AgenteBancario agenteBancario = agenteExistente.get();
        // Atualizar apenas os campos permitidos (sem ID)
        agenteBancario.setNome(dto.getNome());
        agenteBancario.setEmail(dto.getEmail());
        agenteBancario.setCnpjBanco(dto.getCnpjBanco());
        agenteBancario.setNomeBanco(dto.getNomeBanco());
        
        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            agenteBancario.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        AgenteBancario agenteAtualizado = agenteBancarioRepository.save(agenteBancario);
        return Optional.of(new AgenteBancarioResponseDTO(agenteAtualizado));
    }

    public boolean deletar(Long id) {
        if (!agenteBancarioRepository.existsById(id)) {
            return false;
        }
        agenteBancarioRepository.deleteById(id);
        return true;
    }
}