package com.aluguel.carros.model;

import com.aluguel.carros.enums.TipoUsuario;
import jakarta.persistence.*;

@Entity
public class Funcionario extends Usuario {
    @jakarta.validation.constraints.NotBlank(message = "Matrícula é obrigatória")
    private String matricula;

    @Override
    public TipoUsuario getTipoUsuario() {
        return TipoUsuario.FUNCIONARIO;
    }

    // Getters e Setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}