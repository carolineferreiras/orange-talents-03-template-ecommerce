package com.zupacademy.caroline.mercadolivre.Mercado.Livre.Controller;




import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Config.Security.TokenService;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.Request.LoginRequest;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.Response.TokenResponse;
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
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenResponse> autenticar(@RequestBody @Valid LoginRequest request) {
        UsernamePasswordAuthenticationToken dadosLogin = request.converter();

        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
        }
        catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}