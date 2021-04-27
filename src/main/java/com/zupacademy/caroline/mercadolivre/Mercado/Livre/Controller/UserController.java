package com.zupacademy.caroline.mercadolivre.Mercado.Livre.Controller;

import com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.Request.UsuarioRequest;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Repository.UsuarioRepository;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Valid.ProibeUsuarioComEmailDuplicadoValidator;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Valid.UniqueValueValid;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Valid.UniqueValueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;


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


