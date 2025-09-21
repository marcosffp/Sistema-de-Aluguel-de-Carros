package com.aluguel.carros.controller;

import com.aluguel.carros.model.Usuario;
import com.aluguel.carros.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
  @Autowired private UsuarioService usuarioService;

  @GetMapping
  public List<Usuario> listarTodos() {
    return usuarioService.listarTodos();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
    Optional<Usuario> usuario = usuarioService.buscarPorId(id);
    return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
    if (!usuarioService.buscarPorId(id).isPresent()) {
      return ResponseEntity.notFound().build();
    }
    usuario.setId(id);
    return ResponseEntity.ok(usuarioService.salvar(usuario));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    if (!usuarioService.buscarPorId(id).isPresent()) {
      return ResponseEntity.notFound().build();
    }
    usuarioService.deletar(id);
    return ResponseEntity.noContent().build();
  }
}
