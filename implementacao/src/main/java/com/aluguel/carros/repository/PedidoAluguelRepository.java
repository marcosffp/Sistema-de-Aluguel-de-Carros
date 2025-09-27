package com.aluguel.carros.repository;

import com.aluguel.carros.enums.StatusPedido;
import com.aluguel.carros.model.AgenteBancario;
import com.aluguel.carros.model.Cliente;
import com.aluguel.carros.model.Funcionario;
import com.aluguel.carros.model.PedidoAluguel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoAluguelRepository extends JpaRepository<PedidoAluguel, Long> {

  List<PedidoAluguel> findByCliente(Cliente cliente);

  List<PedidoAluguel> findByClienteId(Long clienteId);

  List<PedidoAluguel> findByFuncionario(Funcionario funcionario);

  List<PedidoAluguel> findByAgenteBancario(AgenteBancario agenteBancario);

  List<PedidoAluguel> findByStatus(StatusPedido status);

  @Query("SELECT p FROM PedidoAluguel p WHERE p.status = :status AND p.funcionario IS NULL")
  List<PedidoAluguel> findByStatusAndFuncionarioIsNull(@Param("status") StatusPedido status);

  @Query(
      "SELECT p FROM PedidoAluguel p WHERE p.agenteBancario = :agenteBancario AND p.status IN"
          + " (:statuses)")
  List<PedidoAluguel> findByAgenteBancarioAndStatusIn(
      @Param("agenteBancario") AgenteBancario agenteBancario,
      @Param("statuses") List<StatusPedido> statuses);

  @Query(
      "SELECT p FROM PedidoAluguel p WHERE p.funcionario = :funcionario AND p.status IN"
          + " (:statuses)")
  List<PedidoAluguel> findByFuncionarioAndStatusIn(
      @Param("funcionario") Funcionario funcionario,
      @Param("statuses") List<StatusPedido> statuses);

  @Query("SELECT p FROM PedidoAluguel p WHERE p.cliente = :cliente AND p.status IN (:statuses)")
  List<PedidoAluguel> findByClienteAndStatusIn(
      @Param("cliente") Cliente cliente, @Param("statuses") List<StatusPedido> statuses);

  @Query(
      "SELECT p FROM PedidoAluguel p WHERE p.solicitaCredito = true AND p.status ="
          + " 'AGUARDANDO_ANALISE'")
  List<PedidoAluguel> findPedidosComSolicitacaoCredito();

  @Query(
      "SELECT COUNT(p) FROM PedidoAluguel p WHERE p.cliente = :cliente AND p.status NOT IN"
          + " ('CANCELADO', 'REPROVADO', 'FINALIZADO')")
  Long countPedidosAtivosDoCliente(@Param("cliente") Cliente cliente);
}
