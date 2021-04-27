package com.zupacademy.caroline.mercadolivre.MercadoLivre.Controller;


import com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request.CategoriaRequest;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Categoria;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping
    public String save(@RequestBody @Valid CategoriaRequest request ){
        Categoria categoria = request.toModel(manager);
        manager.persist(categoria);
        return request.toString();
    }

}
