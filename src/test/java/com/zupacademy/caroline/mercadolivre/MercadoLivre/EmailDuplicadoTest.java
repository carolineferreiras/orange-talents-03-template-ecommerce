package com.zupacademy.caroline.mercadolivre.MercadoLivre;


import com.zupacademy.caroline.mercadolivre.MercadoLivre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Repository.UsuarioRepository;
import com.zupacademy.caroline.mercadolivre.MercadoLivre.Valid.UniqueValueValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.stream.Stream;



public class EmailDuplicadoTest{

    @DisplayName("Email unico")
  @ParameterizedTest
  @MethodSource("EmailUnicoCadastro")

    @Test
    public void unicidadeEmailCadastro(Optional<Usuario> optUsuario, boolean esperado){

        //Mockando o repository, para não precisar nos conectar com o banco
        UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);

        //Mockando o resultado do metodo que vai ser chamado pelo validador
        Mockito.when(usuarioRepository.findByEmail("email@teste.com")).thenReturn(optUsuario);

        //utilizando o validador.
        UniqueValueValidator validator = new UniqueValueValidator(usuarioRepository);

        assertEquals (esperado, validator.isValid("email@teste.com", null));
    }

    static Stream<Arguments> geradorUnicidadeEmailCadastro(){
        Optional<Usuario> usuario = Optional.of(new Usuario("email@teste.com", "123456"));
        return Stream.of(Arguments.of(usuario, false),
                Arguments.of(Optional.empty(), true));
    }
}

