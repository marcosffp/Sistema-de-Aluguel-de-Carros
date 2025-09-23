package com.aluguel.carros.config;

import com.aluguel.carros.service.JwtService;
import com.aluguel.carros.service.UsuarioService;
import com.aluguel.carros.model.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;


    @Autowired
    private UsuarioService usuarioService;

    @Override
protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
) throws ServletException, IOException {
    
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String email;

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
    }

    jwt = authHeader.substring(7);
    email = jwtService.extractLogin(jwt);
    if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        var usuarioOpt = usuarioService.buscarPorEmail(email);
        if (usuarioOpt.isPresent() && jwtService.isTokenValid(jwt, email)) {
            Usuario usuario = usuarioOpt.get();
            
            // ✅ CRIAR AUTHORITIES BASEADAS NO TIPO DE USUÁRIO
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getTipoUsuario().name()));
            
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    usuario,
                    null,
                    authorities  // ✅ AGORA COM PERMISSÕES!
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }
    
    filterChain.doFilter(request, response);
}
}