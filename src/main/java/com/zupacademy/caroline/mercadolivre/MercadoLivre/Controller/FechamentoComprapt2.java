package com.zupacademy.caroline.mercadolivre.MercadoLivre.Controller;


import com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request.RetornoPagSeguroRequest;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request.RetornoPayPalRequest;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Compra;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Util.RetornoGatewayPagamento;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class FechamentoComprapt2 {


    @PersistenceContext
    private EntityManager manager;

    @PostMapping(value = "/retorno-pagseguro/{id}")
    @Transactional
     public String confirmaPagamentoPagSeguro(@PathVariable("id") Long idCompra, @RequestBody @Valid RetornoPagSeguroRequest requestPagSeguro){
        return processa(idCompra, requestPagSeguro);
    }


    @PostMapping(value = "/retorno-paypal/{id}")
    @Transactional
    public String confirmaPagamentoPayPal(@PathVariable("id") Long idCompra, @RequestBody @Valid RetornoPayPalRequest requestPaypal){
    return processa(idCompra, requestPaypal);

    }


    private String processa(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamento){

    Compra compra = manager.find(Compra.class, idCompra);
    compra.adicionaTransacao(retornoGatewayPagamento);

    if (compra.processadaComSucesso()){


    }
    manager.merge(compra);
    return compra.toString();

}
}
