package com.aluguel.carros.service;

import com.aluguel.carros.model.Credencial;
import com.aluguel.carros.model.Usuario;
import com.aluguel.carros.repository.CredencialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CredencialService {

    @Autowired
    private CredencialRepository credencialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Credencial criarCredencial(Usuario usuario, String login, String senhaLimpa) {
        Credencial credencial = new Credencial();
        credencial.setId(usuario.getId());
        credencial.setLogin(login);
        credencial.setSenha(passwordEncoder.encode(senhaLimpa));
        credencial.setTipoUsuario(usuario.getTipoUsuario());
        credencial.setUsuario(usuario);
        credencial.setAtiva(true);
        
        return credencialRepository.save(credencial);
    }
}