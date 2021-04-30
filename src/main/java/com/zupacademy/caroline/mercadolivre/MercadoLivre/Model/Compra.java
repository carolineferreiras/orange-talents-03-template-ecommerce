package com.zupacademy.caroline.mercadolivre.MercadoLivre.Model;



import com.zupacademy.caroline.mercadolivre.MercadoLivre.Util.GatewayPagamento;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    @Valid
    private  Produto produtoEscolhido;
    @Positive
    private  int quantidade;
    @ManyToOne
    @NotNull
    @Valid
    private  Usuario comsumidor;
    @Enumerated
    @NotNull
    private  GatewayPagamento gateway;

    @Deprecated
    public Compra() {
    }



    public Compra(Produto produtoASerComprado, int quantidade, Usuario comsumidor, GatewayPagamento gateway) {
        this.produtoEscolhido = produtoASerComprado;
        this.quantidade = quantidade;
        this.comsumidor = comsumidor;
        this.gateway = gateway;
    }


    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produtoEscolhido=" + produtoEscolhido +
                ", quantidade=" + quantidade +
                ", comsumidor=" + comsumidor +
                '}';
    }

    public Long getId() {
        return id;
    }
}
