package com.aluguel.carros.repository;

import com.aluguel.carros.model.AgenteBancario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenteBancarioRepository extends JpaRepository<AgenteBancario, Long> {
}