package com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request;


import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Compra;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Transacao;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Util.RetornoGatewayPagamento;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Util.StatusTransacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RetornoPayPalRequest implements RetornoGatewayPagamento {


    @Min(0)
    @Max(1)
    private int status;
    @NotBlank
    private String idTransacao;


    public RetornoPayPalRequest(int status, String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    public Transacao toTransacao(Compra compra) {
        StatusTransacao statusCalculado = this.status ==0? StatusTransacao.erro:StatusTransacao.sucesso;
        return new Transacao(statusCalculado, idTransacao, compra);
    }
}