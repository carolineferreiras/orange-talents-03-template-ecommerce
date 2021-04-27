package com.zupacademy.caroline.mercadolivre.MercadoLivre.Controller;

import com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request.ImagemRequest;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Produto;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Repository.UsuarioRepository;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Util.Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@Controller
public class ImagemProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Uploader uploaderFake;


    @PostMapping(value = "/produtos/{id}/imagens")
    @Transactional
    //1
    public String adicionaImagens(@PathVariable("id") Long id,@Valid ImagemRequest request) {
        /*
         * 1) enviar imagens para o local onde elas vão ficar
         * 2) pegar os links de todas as imagens		 *
         * 3) associar esses links com o produto em questao
         * 4) preciso carregar o produto
         * 5) depois que associar eu preciso atualizar a nova versão do produto
         */

        Usuario dono = usuarioRepository.findByEmail("email@email.com").get();
        Produto produto = manager.find(Produto.class, id);

        if(!produto.pertenceAoUsuario(dono)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<String> links = uploaderFake.envia(request.getImagens());
        produto.associaImagens(links);

        manager.merge(produto);

        return produto.toString();

    }




}
