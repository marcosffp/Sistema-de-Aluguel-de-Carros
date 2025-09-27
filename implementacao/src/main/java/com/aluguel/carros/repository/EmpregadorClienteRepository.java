package com.aluguel.carros.repository;

import com.aluguel.carros.model.Cliente;
import com.aluguel.carros.model.EmpregadorCliente;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpregadorClienteRepository extends JpaRepository<EmpregadorCliente, Long> {

  List<EmpregadorCliente> findByCliente(Cliente cliente);

  List<EmpregadorCliente> findByClienteId(Long clienteId);

  @Query("SELECT SUM(e.rendimento) FROM EmpregadorCliente e WHERE e.cliente = :cliente")
  BigDecimal calcularRendimentoTotalDoCliente(@Param("cliente") Cliente cliente);

  @Query("SELECT COUNT(e) FROM EmpregadorCliente e WHERE e.cliente = :cliente")
  Long countByCliente(@Param("cliente") Cliente cliente);
}
