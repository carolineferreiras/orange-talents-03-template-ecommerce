package com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request;

import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Opiniao;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Produto;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Valid.ExistsId;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;


public class OpiniaoRequest {

    @Positive
    @Min(value = 1)
    @Max(value = 5)
    private int nota;
    @NotNull
    private String titulo;
    @NotBlank
    @Length(max = 500)
    private String descricao;
    private Long idProduto;

    public @Positive @Min(value = 1) @Max(value = 5) int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    @Deprecated
    public OpiniaoRequest() {
    }

    public OpiniaoRequest(int nota, String titulo, String descricao, Long idProduto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.idProduto = idProduto;
    }

    public Opiniao toModel(Produto produto, Usuario consumidor) {
        return new Opiniao(nota,titulo,descricao, produto, consumidor);}
}
