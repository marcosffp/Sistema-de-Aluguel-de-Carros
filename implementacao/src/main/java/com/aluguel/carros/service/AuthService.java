package com.aluguel.carros.service;

import com.aluguel.carros.dto.AuthResponse;
import com.aluguel.carros.dto.LoginRequest;
import com.aluguel.carros.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthResponse login(LoginRequest request) {
        Usuario usuario = usuarioService.buscarPorEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Email não encontrado"));
        if (!Boolean.TRUE.equals(usuario.getAtivo())) {
            throw new BadCredentialsException("Usuário inativo");
        }
        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            throw new BadCredentialsException("Senha inválida");
        }
        String token = jwtService.generateToken(usuario);
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setTipoUsuario(usuario.getTipoUsuario());
        response.setNome(usuario.getNome());
        response.setExpiresIn(jwtService.getExpirationTime());
        return response;
    }
}