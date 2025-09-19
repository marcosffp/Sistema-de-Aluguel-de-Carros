package com.aluguel.carros.service;

import com.aluguel.carros.dto.AuthResponse;
import com.aluguel.carros.dto.LoginRequest;
import com.aluguel.carros.model.Credencial;
import com.aluguel.carros.repository.CredencialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private CredencialRepository credencialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthResponse login(LoginRequest request) {
        // 1. Buscar credencial
        Credencial credencial = credencialRepository
                .findByLoginAndAtivaTrue(request.getLogin())
                .orElseThrow(() -> new BadCredentialsException("Login inválido"));

        // 2. Verificar senha
        if (!passwordEncoder.matches(request.getSenha(), credencial.getSenha())) {
            credencial.incrementarTentativas();
            if (credencial.getTentativasLogin() >= 5) {
                credencial.setAtiva(false);
            }
            credencialRepository.save(credencial);
            throw new BadCredentialsException("Senha inválida");
        }

        // 3. Reset tentativas e atualizar último login
        credencial.resetTentativas();
        credencial.setUltimoLogin(LocalDateTime.now());
        credencialRepository.save(credencial);

        // 4. Gerar JWT
        String token = jwtService.generateToken(credencial);

        // 5. Criar resposta
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setTipoUsuario(credencial.getTipoUsuario());
        response.setNome(credencial.getUsuario().getNome());
        response.setExpiresIn(jwtService.getExpirationTime());

        return response;
    }
}