package com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request;

import javax.validation.constraints.NotNull;

public class RankingRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idDonoProduto;

    public RankingRequest(Long idCompra, Long idDonoProduto) {
        this.idCompra = idCompra;
        this.idDonoProduto = idDonoProduto;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdDonoProduto() {
        return idDonoProduto;
    }
}
