package com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request;

import javax.validation.constraints.NotNull;

public class NotaFiscalRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public NotaFiscalRequest(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }
}
