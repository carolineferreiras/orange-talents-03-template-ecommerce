package com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model;

import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CaracteristicaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private  String descricao;
    @NotNull @Valid
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Produto produto;

    public CaracteristicaProduto(String nome, String descricao, Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }
}
