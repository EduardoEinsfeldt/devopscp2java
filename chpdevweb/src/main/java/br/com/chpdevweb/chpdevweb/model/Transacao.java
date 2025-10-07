package br.com.chpdevweb.chpdevweb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transacoes")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_transacao_usuario"))
    private Usuario usuario;

    @Column(nullable = false)
    private double valor;

    public Transacao() {}

    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
}