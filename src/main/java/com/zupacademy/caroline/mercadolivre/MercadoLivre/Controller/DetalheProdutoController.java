package com.zupacademy.caroline.mercadolivre.MercadoLivre.Controller;


import com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Response.DetalheProdutoView;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class DetalheProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping (value = "/produtos/{id}")
    public DetalheProdutoView busca(@PathVariable("id") Long id){
        Produto produtoEscolhido = manager.find(Produto.class,id);
        return new DetalheProdutoView(produtoEscolhido);

    }
}
