package com.zupacademy.caroline.mercadolivre.Mercado.Livre.Controller;




import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Config.Security.TokenService;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.TokenDTO;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.UsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private TokenService tokenService;

@PostMapping
    ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid UsuarioRequest request){

    UsernamePasswordAuthenticationToken dadosLogin = request.converter();

    try {
        Authentication authentication =authenticationManager.authenticate(dadosLogin);
        String token = tokenService.gerarToken(authentication);
        return ResponseEntity.ok(new TokenDTO(token,"Bearer"));

    }catch (AuthenticationException e){
    return ResponseEntity.badRequest().build();
    }

}

    }

