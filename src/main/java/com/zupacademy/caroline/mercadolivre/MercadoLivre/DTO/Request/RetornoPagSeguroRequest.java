package com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request;

import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Compra;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Transacao;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Util.RetornoGatewayPagamento;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Util.StatusRetornoPagSeguro;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagSeguroRequest  implements RetornoGatewayPagamento {

    @NotBlank
    private String idTransacao;
    @NotNull
    private StatusRetornoPagSeguro status;

    public RetornoPagSeguroRequest(String idTransacao, StatusRetornoPagSeguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "RetornoPagseguroRequest [idTransacao=" + idTransacao
                + ", status=" + status + "]";
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(),idTransacao, compra);
    }

}