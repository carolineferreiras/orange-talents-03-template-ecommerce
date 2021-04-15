package com.zupacademy.caroline.mercadolivre.Mercado.Livre.Controller;



import com.zupacademy.caroline.mercadolivre.Mercado.Livre.DTO.UsuarioRequest;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Model.Usuario;
import com.zupacademy.caroline.mercadolivre.Mercado.Livre.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioRequest requestDTO ){
        Usuario usuario = requestDTO.converter();
        return ResponseEntity.ok().body((usuarioRepository.save(usuario)));
    }

    @GetMapping
    public List<Usuario> Get() {
        return usuarioRepository.findAll();
    }
}
