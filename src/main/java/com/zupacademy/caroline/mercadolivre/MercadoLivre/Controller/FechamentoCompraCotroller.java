package com.zupacademy.caroline.mercadolivre.MercadoLivre.Controller;

import com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request.NovaCompraRequest;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Compra;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Produto;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Repository.UsuarioRepository;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Util.GatewayPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class FechamentoCompraCotroller {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(value = "/compras")
    @Transactional
    public String fechaCompra(@RequestBody @Valid NovaCompraRequest request , UriComponentsBuilder uriComponentsBuilder) throws BindException {

        Produto produtoASerComprado = manager.find(Produto.class, request.getIdProduto());
        int quantidade = request.getQuantidade();
        boolean abateu = produtoASerComprado.abateDoEstoque(quantidade);

        if (abateu) {
            Usuario comsumidor = usuarioRepository.findByEmail("email@email.com").get();
            Compra novaCompra = new Compra(produtoASerComprado, quantidade, comsumidor, request.getGateway());
            GatewayPagamento gateway = request.getGateway();
            manager.persist(novaCompra);
            if (gateway.equals(GatewayPagamento.pagseguro)){
                UriComponents urlRetornoPagseguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(novaCompra.getId().toString());
                return  "pagseguro.com?returnId=" + novaCompra.getId() + "redirectUrl="+urlRetornoPagseguro;
            }else {
                UriComponents urlRetornoPaypal= uriComponentsBuilder.path("/retorno-paypal/{id}").buildAndExpand(novaCompra.getId().toString());
                return  "paypal.com?buyerId=" + novaCompra.getId() + "redirectUrl="+urlRetornoPaypal;


            }
        }

        BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
        problemaComEstoque.reject(null,"Não foi possivel finalizar a compra, não possui em estoque");

        throw problemaComEstoque;
    }
}
