package com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

public class SenhaLimpa {
    private String senha;

    public SenhaLimpa(String senha) {
        Assert.hasLength(senha,"Senha não pode ser em branco");
        Assert.isTrue(senha.length()>=6, "Senha tem que ter no minimo 6 caracteres");
        this.senha = senha;
    }

    public String hash(){
        return new BCryptPasswordEncoder().encode(senha);
    }
}
