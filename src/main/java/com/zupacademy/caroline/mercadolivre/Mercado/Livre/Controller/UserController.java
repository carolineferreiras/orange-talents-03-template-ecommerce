package com.zupacademy.caroline.mercadolivre.Mercado.Livre.Controller;

import com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.Request.UsuarioRequest;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/usuario")
public class UserController {

    private UsuarioRepository usuarioRepository;

    public UserController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    @PostMapping
    private ResponseEntity<?> cadastrar (@RequestBody @Valid UsuarioRequest usuarioRequest){

        Usuario usuario = usuarioRequest.toModel();
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}")
    private ResponseEntity<?> detalhar  (@PathVariable Long id){

        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();


    }



}


