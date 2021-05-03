package com.zupacademy.caroline.mercadolivre.MercadoLivre.Util;

import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Compra;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Transacao;

public interface RetornoGatewayPagamento {

    /**
     *
     * @param compra
     * @return uma nova transacao em função do gateway específico
     */

    Transacao toTransacao(Compra compra);
}
