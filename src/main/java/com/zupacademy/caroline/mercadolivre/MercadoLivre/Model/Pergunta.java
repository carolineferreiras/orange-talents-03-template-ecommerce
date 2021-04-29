package com.zupacademy.caroline.mercadolivre.MercadoLivre.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Pergunta implements Comparable<Pergunta> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private  String titulo;
    @ManyToOne
    private Usuario consumidor;
    @ManyToOne
    private Produto produto;


    @Deprecated
    public Pergunta() {
    }

    public Pergunta(String titulo, Usuario consumidor, Produto produto) {
        this.titulo = titulo;
        this.consumidor = consumidor;
        this.produto = produto;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pergunta pergunta = (Pergunta) o;
        return Objects.equals(id, pergunta.id) && Objects.equals(titulo, pergunta.titulo) && Objects.equals(consumidor, pergunta.consumidor) && Objects.equals(produto, pergunta.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, consumidor, produto);
    }

    @Override
    public int compareTo(Pergunta o) {
        return this.titulo.compareTo(o.titulo);
    }
}
