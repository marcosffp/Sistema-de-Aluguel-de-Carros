package com.aluguel.carros.service;

import com.aluguel.carros.model.Usuario;
import com.aluguel.carros.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioService {
  
  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private UsuarioRepository usuarioRepository;

  public Optional<Usuario> buscarPorEmail(String email) {
    return usuarioRepository.findByEmail(email);
  }

  public List<Usuario> listarTodos() {
    return usuarioRepository.findAll();
  }

  public Optional<Usuario> buscarPorId(Long id) {
    return usuarioRepository.findById(id);
  }

  public Usuario salvar(Usuario usuario) {
    if (usuario.getSenha() != null) {
      String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
      usuario.setSenha(senhaCriptografada);
    }
    return usuarioRepository.save(usuario);
  }

  public void deletar(Long id) {
    usuarioRepository.deleteById(id);
  }
}
