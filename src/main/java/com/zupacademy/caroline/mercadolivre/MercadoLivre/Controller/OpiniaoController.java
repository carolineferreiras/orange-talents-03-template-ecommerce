package com.zupacademy.caroline.mercadolivre.MercadoLivre.Controller;

import com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request.OpiniaoRequest;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Opiniao;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Produto;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class OpiniaoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(value = "/produtos/{id}/opiniao")
    @Transactional
    public String cria(@RequestBody @Valid OpiniaoRequest request , @PathVariable("id")Long id){
         Produto produto = manager.find(Produto.class, id);
        //simulando o usuario logado
        Usuario comsumidor = usuarioRepository.findByEmail("email@email.com").get();
       Opiniao opiniao = request.toModel(produto,comsumidor);
       manager.persist(opiniao);
        return opiniao.toString();
    }
}