package br.com.chpdevweb.chpdevweb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    public Usuario() {}
    public Usuario(String nome) { this.nome = nome; }
    public Long getId() { return id; }
    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public void setId(Long id) {
        this.id = id;
    }
}