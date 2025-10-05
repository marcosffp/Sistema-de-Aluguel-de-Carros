package com.aluguel.carros.model;

import com.aluguel.carros.enums.TipoProprietario;

public class Proprietario {
    private String identificador;
    private String nome;
    private TipoProprietario tipoProprietario; // Enum: CLIENTE ou BANCO ou EMPRESA

    public Proprietario(String identificador, String nome) {
        this.identificador = identificador;
        this.nome = nome;
        this.tipoProprietario = TipoProprietario.EMPRESA; // Define como EMPRESA por padrão
    }

    // Construtor, getters e setters
    public Proprietario(String identificador, String nome, TipoProprietario tipoProprietario) {
        this.identificador = identificador;
        this.nome = nome;
        this.tipoProprietario = tipoProprietario;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoProprietario getTipoProprietario() {
        return tipoProprietario;
    }

    public void setTipoProprietario(TipoProprietario tipoProprietario) {
        this.tipoProprietario = tipoProprietario;
    }
  }