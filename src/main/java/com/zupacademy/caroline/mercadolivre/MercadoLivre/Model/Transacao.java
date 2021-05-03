package com.zupacademy.caroline.mercadolivre.MercadoLivre.Model;

import com.zupacademy.caroline.mercadolivre.MercadoLivre.Util.StatusTransacao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private  LocalDateTime instante;
    @NotNull
    private StatusTransacao status;
    @NotBlank
    private  String idTransacaoGateway;
    @ManyToOne
    private Compra compra;

    @Deprecated
    public Transacao() {
    }

    public Transacao(StatusTransacao normaliza, String idTransacao, Compra compra) {
        this.instante = LocalDateTime.now();
        this.status = normaliza;
        this.idTransacaoGateway = idTransacaoGateway;
        this.compra = compra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(id, transacao.id) && Objects.equals(instante, transacao.instante) && status == transacao.status && Objects.equals(idTransacaoGateway, transacao.idTransacaoGateway) && Objects.equals(compra, transacao.compra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instante, status, idTransacaoGateway, compra);
    }

    public boolean concluidaComSucesso() {
        return this.status.equals(StatusTransacao.sucesso);
    }



}


