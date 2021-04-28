package com.zupacademy.caroline.mercadolivre.MercadoLivre.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Pergunta {

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


}
