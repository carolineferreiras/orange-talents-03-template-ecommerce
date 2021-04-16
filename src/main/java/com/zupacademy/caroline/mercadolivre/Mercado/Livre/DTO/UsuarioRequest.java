package com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO;

import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.SenhaLimpa;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Valid.UniqueValueValid;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioRequest {

    @NotBlank @Email
    @UniqueValueValid(field = "email", classe = Usuario.class)
    private String email;
    @NotBlank @Length(min = 6)
    private String senha;

    public UsuarioRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario converter() {
        return new Usuario(this.email,new SenhaLimpa(new BCryptPasswordEncoder().encode(senha)));
    }

    public @NotBlank @Email String getClass(Class<UsuarioRequest> usuarioRequestClass) {
    return email;}
}
