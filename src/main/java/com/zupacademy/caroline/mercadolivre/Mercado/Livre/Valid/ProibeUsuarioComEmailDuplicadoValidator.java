package com.zupacademy.caroline.mercadolivre.Mercado.Livre.Valid;

import com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.Request.UsuarioRequest;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Repository.UsuarioRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeUsuarioComEmailDuplicadoValidator implements Validator {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean supports(Class<?> clazz) {

        return UsuarioRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        UsuarioRequest request = (UsuarioRequest) target;

        Optional<Usuario> possivelUsuario = usuarioRepository.findByEmail(request.getEmail());

        if(possivelUsuario.isPresent()) {
            errors.rejectValue("email",null, "ja existe este email no sistema");
        }
    }

}