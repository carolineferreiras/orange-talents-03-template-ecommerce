package com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request;

import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.CaracteristicaProduto;

public class DetalheCaracteristicaProduto {


    private  String nome;
    private  String descricao;

    public DetalheCaracteristicaProduto(CaracteristicaProduto caracteristica) {
    this.nome = caracteristica.getNome();
    this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
