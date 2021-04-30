package com.zupacademy.caroline.mercadolivre.MercadoLivre;

import com.zupacademy.caroline.mercadolivre.MercadoLivre.DTO.Request.CaracteristicaRequest;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Categoria;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Produto;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Util.SenhaLimpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.math.BigDecimal;
import java.util.List;

public class CompraTest {

    @Test
    @DisplayName("Não aceita abater estoque <= zero")
    void testeEstoque1( int estoque){
        List<CaracteristicaRequest> caracteristicaRequests = List.of(
                new CaracteristicaRequest("key 1" , "value 1"),
                new CaracteristicaRequest("key 2" , "value 2"),
                new CaracteristicaRequest("key 3" , "value 3"));
        Categoria categoria = new Categoria("categoria");
        Usuario dono = new Usuario("email@email.com",new SenhaLimpa("1234567"));
        Produto produto= new Produto("nome",10,"descricao", BigDecimal.TEN,categoria,dono,caracteristicaRequests);

        Assertions.assertThrows(IllegalArgumentException.class, () -> produto.abateDoEstoque(estoque));
    }


    @DisplayName("Verifica estoque do produto")
    void testeEstoque2( int estoque, int quantidadePedida , boolean resuldatadoEsperado){
    List<CaracteristicaRequest> caracteristicaRequests = List.of(
            new CaracteristicaRequest("key 1" , "value 1"),
            new CaracteristicaRequest("key 2" , "value 2"),
            new CaracteristicaRequest("key 3" , "value 3"));
    Categoria categoria = new Categoria("categoria");
    Usuario dono = new Usuario("email@email.com",new SenhaLimpa("1234567"));
        Produto produto= new Produto("nome",estoque,"descricao", BigDecimal.TEN,categoria,dono,caracteristicaRequests);

    boolean resultado = produto.abateDoEstoque(quantidadePedida);
}
}
