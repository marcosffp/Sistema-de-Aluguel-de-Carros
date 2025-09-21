package com.aluguel.carros.service;

import com.aluguel.carros.model.AgenteBancario;
import com.aluguel.carros.repository.AgenteBancarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenteBancarioService {
    @Autowired
    private AgenteBancarioRepository agenteBancarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<AgenteBancario> listarTodos() {
        return agenteBancarioRepository.findAll();
    }

    public Optional<AgenteBancario> buscarPorId(Long id) {
        return agenteBancarioRepository.findById(id);
    }

    public AgenteBancario salvar(AgenteBancario agenteBancario) {
        if (agenteBancario.getSenha() != null) {
            String senhaCriptografada = passwordEncoder.encode(agenteBancario.getSenha());
            agenteBancario.setSenha(senhaCriptografada);
        }
        return agenteBancarioRepository.save(agenteBancario);
    }

    public void deletar(Long id) {
        agenteBancarioRepository.deleteById(id);
    }
}