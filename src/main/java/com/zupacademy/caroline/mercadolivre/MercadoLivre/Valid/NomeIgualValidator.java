package com.zupacademy.caroline.mercadolivre.MercadoLivre.Valid;

import com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request.ProdutoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class NomeIgualValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProdutoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()){
            return;
        }
    ProdutoRequest request = (ProdutoRequest) target;
       Set<String> nomesIguais = request.temCaracteristicasIguais();
        if (!nomesIguais.isEmpty()){
        errors.rejectValue("caracteristicas",null,"Você possui caracteristicas iguais" + nomesIguais);
        }
    }
}
