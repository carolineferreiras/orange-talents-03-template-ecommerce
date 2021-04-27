package com.zupacademy.caroline.mercadolivre.MercadoLivre.Controller;


import com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request.ProdutoRequest;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Produto;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Repository.UsuarioRepository;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Valid.NomeIgualValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ProdutoController {


    @PersistenceContext
   private EntityManager manager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators( new NomeIgualValidator());
    }


    @PostMapping(value = "/produtos")
    @Transactional
    public String cria(@RequestBody @Valid ProdutoRequest request) {
        //simulando o usuario logado
        Usuario dono = usuarioRepository.findByEmail("email@email.com").get();
        Produto produto = request.toModel(manager,dono);
        manager.persist(produto);
        return produto.toString();
    }



}

