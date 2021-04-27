package com.zupacademy.caroline.mercadolivre.Mercado.Livre.Controller;


import com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.Request.ImagemRequest;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.Request.ProdutoRequest;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Produto;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Repository.UsuarioRepository;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Valid.NomeIgualValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {


    @PersistenceContext
   private EntityManager manager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators( new NomeIgualValidator());
    }


    @Transactional
    @PostMapping
    public String cadastrar (@RequestBody @Valid ProdutoRequest request){
        //simula user logado
        Usuario usuario  = usuarioRepository.findByEmail("email@email.com").get();
        Produto produto = request.converter(manager, usuario);

        manager.persist(produto);
        return produto.toString();

    }

    @PostMapping(value = "produtos/{id}/imagens")
    public String adicionaImagens(@PathVariable("id") Long id,  @Valid ImagemRequest request) {
        return request;
    }


}

