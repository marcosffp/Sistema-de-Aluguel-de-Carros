package com.aluguel.carros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aluguel.carros.dto.AuthResponse;
import com.aluguel.carros.dto.FuncionarioRequestDTO;
import com.aluguel.carros.dto.FuncionarioResponseDTO;
import com.aluguel.carros.model.Funcionario;
import com.aluguel.carros.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public List<FuncionarioResponseDTO> listarTodos() {
        return funcionarioRepository.findAll().stream()
                .map(FuncionarioResponseDTO::new)
                .toList();
    }

    public Optional<FuncionarioResponseDTO> buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .map(FuncionarioResponseDTO::new);
    }

    public AuthResponse criar(FuncionarioRequestDTO dto) {
        Funcionario funcionario = new Funcionario(dto.getNome(), dto.getEmail(), 
                dto.getSenha(), dto.getMatricula());
        
        if (funcionario.getSenha() != null) {
            String senhaCriptografada = passwordEncoder.encode(funcionario.getSenha());
            funcionario.setSenha(senhaCriptografada);
        }
        
        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);

        // Gerar token
        String token = jwtService.generateToken(funcionarioSalvo);

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setTipoUsuario(funcionarioSalvo.getTipoUsuario());
        response.setNome(funcionarioSalvo.getNome());
        response.setExpiresIn(jwtService.getExpirationTime());

        return response;
    }

    public Optional<FuncionarioResponseDTO> atualizar(Long id, FuncionarioRequestDTO dto) {
        Optional<Funcionario> funcionarioExistente = funcionarioRepository.findById(id);
        if (funcionarioExistente.isEmpty()) {
            return Optional.empty();
        }

        Funcionario funcionario = funcionarioExistente.get();
        // Atualizar apenas os campos permitidos (sem ID)
        funcionario.setNome(dto.getNome());
        funcionario.setEmail(dto.getEmail());
        funcionario.setMatricula(dto.getMatricula());
        
        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            funcionario.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        Funcionario funcionarioAtualizado = funcionarioRepository.save(funcionario);
        return Optional.of(new FuncionarioResponseDTO(funcionarioAtualizado));
    }

    public boolean deletar(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            return false;
        }
        funcionarioRepository.deleteById(id);
        return true;
    }
}