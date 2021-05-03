package com.zupacademy.caroline.mercadolivre.MercadoLivre.Model;



import com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request.RetornoPagSeguroRequest;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Util.GatewayPagamento;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Util.RetornoGatewayPagamento;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "compra",cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {
    }



    public Compra(Produto produtoASerComprado, int quantidade, Usuario comsumidor, GatewayPagamento gateway) {
        this.produtoEscolhido = produtoASerComprado;
        this.quantidade = quantidade;
        this.comsumidor = comsumidor;
        this.gateway = gateway;
    }

    public Long getId() {
        return id;
    }

    public void adicionaTransacao(@Valid RetornoGatewayPagamento request) {
        Transacao novaTransacao = request.toTransacao(this);
        Assert.state(!this.transacoes.contains(novaTransacao),
                "Já existe uma transacao processada "
                        + novaTransacao);
        Assert.state(transacoesConcluidasComSucesso().isEmpty(),"Esse compra já foi concluída com sucesso");
        this.transacoes.add(novaTransacao);
    }


    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,"Favor veridicar a transação"+this.id);
        return transacoesConcluidasComSucesso;
    }


    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }
}
