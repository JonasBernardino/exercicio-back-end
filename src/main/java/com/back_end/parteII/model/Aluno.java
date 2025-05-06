package com.back_end.parteII.model;

public class Aluno {
    private Long id;
    private String nome;
    private String email;
    private double nota;
    private Endereco endereco;

    public Aluno() {
    }

    public Aluno(Long id, String nome, String email, double nota) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
