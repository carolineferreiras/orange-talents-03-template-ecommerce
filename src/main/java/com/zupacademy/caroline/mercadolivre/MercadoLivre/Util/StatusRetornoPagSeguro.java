package com.zupacademy.caroline.mercadolivre.MercadoLivre.Util;

public enum StatusRetornoPagSeguro {

    SUCESSO, ERRO;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)){
            return StatusTransacao.sucesso;
        }
            return StatusTransacao.erro;
    }
}
