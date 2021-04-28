package com.zupacademy.caroline.mercadolivre.MercadoLivre.Model;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Positive
    @Min(value = 1)
    @Max(value = 5)
    private int nota;
    @NotNull
    private String titulo;
    @NotNull
    @Length(max =  500)
    private String descricao;
    @Valid
    @ManyToOne
    private Usuario consumidor;
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Opiniao() {
    }


    public Opiniao(int nota, String titulo, String descricao, Produto produto, Usuario consumidor) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
    }


    @Override
    public String toString() {
        return "Opiniao{" +
                "id=" + id +
                ", nota='" + nota + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", usuario=" + consumidor +
                ", produto=" + produto +
                '}';
    }
}
