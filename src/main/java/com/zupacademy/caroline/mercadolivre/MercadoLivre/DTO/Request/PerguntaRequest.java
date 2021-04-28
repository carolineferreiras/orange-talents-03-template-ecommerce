package com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request;


import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Pergunta;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Produto;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Usuario;

import javax.validation.constraints.NotBlank;


public class PerguntaRequest {

    @NotBlank
    private String  titulo;

    @Deprecated
    public PerguntaRequest() {
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel(Produto produto, Usuario consumidor) {
        return new Pergunta(titulo,consumidor,produto);
    }
}

