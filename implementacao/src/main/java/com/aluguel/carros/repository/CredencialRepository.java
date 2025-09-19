package com.aluguel.carros.repository;

import com.aluguel.carros.model.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Long> {
    Optional<Credencial> findByLoginAndAtivaTrue(String login);
    Optional<Credencial> findByLogin(String login);
}