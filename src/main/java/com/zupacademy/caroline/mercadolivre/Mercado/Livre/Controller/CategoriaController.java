package com.zupacademy.caroline.mercadolivre.Mercado.Livre.Controller;


import com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.CategoriaRequest;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Categoria;
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
