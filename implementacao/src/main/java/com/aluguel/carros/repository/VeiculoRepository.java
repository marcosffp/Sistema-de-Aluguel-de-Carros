package com.aluguel.carros.repository;

import com.aluguel.carros.model.Veiculo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
  Optional<Veiculo> findByMatricula(String matricula);

  Optional<Veiculo> findByPlaca(String placa);

  List<Veiculo> findByDisponivel(Boolean disponivel);

  @Query("SELECT v FROM Veiculo v WHERE v.disponivel = true")
  List<Veiculo> findVeiculosDisponiveis();

  @Query("SELECT v FROM Veiculo v WHERE LOWER(v.marca) LIKE LOWER(CONCAT('%', :marca, '%'))")
  List<Veiculo> findByMarcaContaining(String marca);

  @Query("SELECT v FROM Veiculo v WHERE LOWER(v.modelo) LIKE LOWER(CONCAT('%', :modelo, '%'))")
  List<Veiculo> findByModeloContaining(String modelo);
}
