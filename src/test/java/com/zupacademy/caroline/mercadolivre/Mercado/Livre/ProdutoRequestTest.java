package com.zupacademy.caroline.mercadolivre.Mercado.Livre;


import com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.Request.CaracteristicaRequest;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.Request.ProdutoRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public class ProdutoRequestTest {


    @DisplayName("Um produto com diversas caracteristicas")
    @ParameterizedTest
    @MethodSource("gerador")
    void teste1(int esperado, List<CaracteristicaRequest> caracteristicaRequests) throws Throwable {
        ProdutoRequest request = new ProdutoRequest("nome", 10, "descricao", BigDecimal.TEN ,1l, caracteristicaRequests);

        Assertions.assertEquals(esperado,request.temCaracteristicasIguais().size());
    }

    private static Stream<Arguments> gerador(){
        return Stream.of(
                //não tem caracteristica
                Arguments.of(0,List.of()),
                //Uma caracteristicas
                Arguments.of(0,List.of(new CaracteristicaRequest("key","value"))),
                //Duas caracteristicas diferentes uma da outra
                Arguments.of(0,List.of(new CaracteristicaRequest("key","value"),new CaracteristicaRequest("key1","value1"))),
                //Duas caracteristicas iguais
                Arguments.of(1,List.of(
                new CaracteristicaRequest("key","value"),
                        new CaracteristicaRequest("key","value")))
                );
    }
}
