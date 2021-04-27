package com.zupacademy.caroline.mercadolivre.MercadoLivre.Controller;

import com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request.UsuarioRequest;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Valid.ProibeUsuarioComEmailDuplicadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping("/usuario")
public class UserController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private ProibeUsuarioComEmailDuplicadoValidator proibeUsuarioComEmailDuplicadoValidator;


    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(proibeUsuarioComEmailDuplicadoValidator);
    }


    @PostMapping
    @Transactional
    public String cria(@RequestBody @Valid UsuarioRequest request) {
        Usuario novoUsuario = request.toModel();
        manager.persist(novoUsuario);
        return novoUsuario.toString();
    }




}


