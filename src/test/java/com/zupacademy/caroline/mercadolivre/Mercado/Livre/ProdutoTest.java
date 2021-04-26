package com.zupacademy.caroline.mercadolivre.Mercado.Livre;

import com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.Request.CaracteristicaRequest;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Categoria;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Produto;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Util.SenhaLimpa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;


public class ProdutoTest{


    @DisplayName("Um produto precisa ter no minimo 3 caracteristicas")
    @ParameterizedTest
    @MethodSource("Teste1")
    void teste1(Collection<CaracteristicaRequest> caracteristicaRequests)
            throws Exception {
        Categoria categoria = new Categoria("categoria");
        Usuario usuario = new Usuario("email@email.com" ,
                new SenhaLimpa("123456789"));

        new Produto("nome", 10 ,"descricao", BigDecimal.TEN , categoria,usuario, caracteristicaRequests);
    }

    static Stream<Arguments> geradorTeste1(){
        return Stream.of(
                Arguments.of(
                        List.of(new CaracteristicaRequest("key","value"),
                                new CaracteristicaRequest("key2","value2"),
                                new CaracteristicaRequest("key3","value3"))),
                //
                Arguments.of(List.of(
                        new CaracteristicaRequest("key","value"),
                        new CaracteristicaRequest("key2","value2"),
                        new CaracteristicaRequest("key3","value3"),
                        new CaracteristicaRequest("key4","value4"))));
    }

    @DisplayName("Um produto não pode ser criado com menos de 3 categorias")
    @ParameterizedTest
    //Sinaliza que o método anotado é um método de teste parametrizado

    @MethodSource("GeradorTeste2")
    // fornece acesso a valores retornados de métodos de fábrica da
    // classe na qual essa anotação é declarada ou de métodos de fábrica estáticos
    // em classes externas referenciadas por um nome de método totalmente qualificado .

     void teste2(Collection<CaracteristicaRequest> caracteristicaRequests) throws Exception {
         Categoria categoria = new Categoria("categoria");
         Usuario usuario = new Usuario("email@email.com",
                 new SenhaLimpa("123456789"));

         Assertions.assertThrows(IllegalArgumentException.class, () -> {
             new Produto("nome", 10, "descricao", BigDecimal.TEN, categoria, usuario, caracteristicaRequests);
         });
     }
         static Stream<Arguments> geradorTeste2(){
             return Stream.of(
                     Arguments.of(
                             List.of(new CaracteristicaRequest("key","value"),
                                     new CaracteristicaRequest("key2","value2"))),

                     //
                     Arguments.of(List.of(
                             new CaracteristicaRequest("key","value"))));
    }
}
