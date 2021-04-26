package com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.Response;

import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Usuario;

public class UsuarioResponse {


    private String nome;

    public String getNome() {
        return nome;
    }

    public UsuarioResponse(String nome) {

        this.nome = nome;
    }


    public UsuarioResponse() {

    }

    public UsuarioResponse(Usuario usuario) {
        this.nome = usuario.getEmail();
    }

}
