package com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request;


import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Util.SenhaLimpa;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Valid.UniqueValueValid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import org.springframework.util.StringUtils;

public class UsuarioRequest {

    @NotBlank
    @Email
    @UniqueValueValid(classe = Usuario.class, field = "email")
    private String email;
    @NotBlank
    @Length(min=6)
    private String senha;

    @Deprecated
    public UsuarioRequest() {
    }

    public UsuarioRequest(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha) {
        Assert.isTrue(StringUtils.hasLength(login),"Nao pode ser em branco");
        Assert.isTrue(StringUtils.hasLength(senha),"Nao pode ser em branco");
        Assert.isTrue(senha.length() >= 6,"Nao pode ser menor que 6");


        this.email = email;
        this.senha = senha;
    }
    public String getEmail() {
        return email;
    }
    public String getSenha() {
        return senha;
    }

    public Usuario toModel() {
        return new Usuario(email, new SenhaLimpa(senha));
    }


}